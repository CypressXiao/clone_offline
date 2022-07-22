#!bin/bash

# 将HDFS  T-1日收集到的日志条数上报

# 上报的数据是 T-1日   2022-07-21
DT=$(date -d'-1 days'  +%Y-%m-%d)
# 用户指定上报日志的日志  2022-07-19
if [ $1 ]; then
    DT=$1
fi
# 1 当前服务器的主机名
SERVERNAME=HDFS
LOGLINE=$(hdfs  dfs  -text /doe/data/app_log/doitedu02/${DT}/* | wc -l)
curl  http://windows:8081/api/report  -X POST   -d"{\"serverName\":\"HDFS\" , \"logType\":\"applogs\" , \"logCount\":\"${LOGLINE}\" , \"dt\":\"${DT}\"}" -H "Content-Type: application/json"
if [ $? -eq 0 ]; then
   echo  "上报成功...."
   # 发送邮件    from[组长]    to  [你]
   echo "${SERVERNAME} 上报的日志日期是 ${DT} 上报成功......."  |  mail -s  "日志上报成功!"  598196583@qq.com
   else
     echo "上报失败.."
   echo "${SERVERNAME}上报的日志日期是 ${DT} 上报失败 , 失败原因未知......."  |  mail -s  "日志上报失败!"  598196583@qq.com
fi