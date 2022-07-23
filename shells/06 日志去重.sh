#!/bin/bash
#  根据日志类型和日期  判断某天的某中日志采集量是重复了 , 延迟
#  如果数据重复  去重
#  如果数据有延迟  后续的工作 推迟

# 1 请求数据服务  获取各个日志服务器生成的日志总行数据
# 2 请求数据服务  获取HDFS采集到总数据的行数据
# 对比判断

DT=$(date -d'-1 days'  +%Y-%m-%d)
# 用户指定上报日志的日志  2022-07-19
# read  -p "请输入去重的日期: " DT
if [ $1 ]; then
    DT=$1
fi

# 日志类型  这里使用默认的日志类型
LOGTYPE=applogs


#获取日志服务器的日志条数
LOGCNT=$(curl  http://windows:8081/api/getlogLines  -X POST   -d"{\"logType\":\"${LOGTYPE}\" , \"dt\":\"${DT}\"}" -H "Content-Type: application/json")

# 获取HDFS的日志条数
HDFSCNT=$(curl  http://windows:8081/api/getHdfsLogLines  -X POST   -d"{\"dt\":\"${DT}\"}" -H "Content-Type: application/json")

echo  "HDFS采集到的日志条数: $HDFSCNT"
echo  "HDFS采集到的日志条数: $LOGCNT"
#前提是 先创建一张表
    # drop table if exists doe.tmp_raw_applog ;
    #create external table if not exists doe.tmp_raw_applog(
    #    line string
    #)
    # partitioned by (dt string)
    # location '/doe/data/app_log/' ;

# doe.tmp_raw_applog  加载原始日志数据
hive -e "alter  table  doe.tmp_raw_applog   add  if not exists   partition (dt='${DT}') location  '/doe/data/app_log/${DT}'"
if [  $HDFSCNT  -gt $LOGCNT ]; then
    echo  "需要去重"
     # 指定输出压缩格式
   sql2="set hive.exec.compress.output = true;
       set mapred.output.compression.codec = org.apache.hadoop.io.compress.GzipCodec;
       insert  overwrite  table  doe.tmp_raw_applog partition (dt='${DT}')
       select  distinct line from doe.tmp_raw_applog  where  dt = '${DT}'"
    hive -e  "${sql2}"
  else
    echo "不需要去重"
fi

