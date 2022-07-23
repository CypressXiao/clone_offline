#!/bin/bash
#  根据日志类型和日期  判断某天的某中日志采集量是重复了 , 延迟
#  如果数据重复  去重
#  如果数据有延迟  后续的工作 推迟

# 1 请求数据服务  获取各个日志服务器生成的日志总行数据
# 2 请求数据服务  获取HDFS采集到总数据的行数据
# 对比判断

DT=$(date -d'-1 days'  +%Y-%m-%d)
# 用户指定上报日志的日志  2022-07-19
if [ $1 ]; then
    DT=$1
fi
# read  -p "请输入日志类型: " LOGTYPE
# 日志类型  这里使用默认的日志类型
LOGTYPE=applogs


#获取日志服务器的日志条数
LOGCNT=$(curl  http://windows:8081/api/getlogLines  -X POST   -d"{\"logType\":\"${LOGTYPE}\" , \"dt\":\"${DT}\"}" -H "Content-Type: application/json")

# 获取HDFS的日志条数
HDFSCNT=$(curl  http://windows:8081/api/getHdfsLogLines  -X POST   -d"{\"dt\":\"${DT}\"}" -H "Content-Type: application/json")

echo  "HDFS采集到的日志条数: $HDFSCNT"
echo  "HDFS采集到的日志条数: $LOGCNT"

if [  $HDFSCNT  -gt $LOGCNT ]; then
    echo  "需要去重"
  else
    echo "不需要去重"
fi

