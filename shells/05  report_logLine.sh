#!/bin/bash
#----------------------------------------------------------------
#            上报各种日志数据的日志条数
#  请求的服务地址  http://windows:8081/api/reportLogLine
#  请求的参数: 当前服务器  日志类型   上报日期  日志行数
#----------------------------------------------------------------
# 上报的数据是 T-1日   2022-07-21
DT=$(date -d'-1 days'  +%Y-%m-%d)
# 用户指定上报日志的日志  2022-07-19
if [ $1 ]; then
    DT=$1
fi
# 1 当前服务器的主机名
SERVERNAME=$HOSTNAME
# 2 当前上报的日志类型
  # 判断数据目录下是否有日志文件夹
if [ $(ls  /doe/data | wc -l) -gt 1 ]; then
  for dir in  $(ls /doe/data)
  do
     echo "日志类型是: "${dir}
     LOGTYPE=${dir}
     LOGLINE=$(cat /doe/data/${dir}/*.${DT}  | wc -l)
     echo "${SERVERNAME} 上报的日志日期是 ${DT} 日志类型: ${LOGTYPE}  日志行数: ${LOGLINE}"
      # 3 开始将数据上报到服务器
      curl  http://windows:8081/api/report  -X POST   -d"{\"serverName\":\"${SERVERNAME}\" , \"logType\":\"${LOGTYPE} \" , \"logCount\":\"${LOGLINE}\" , \"dt\":\"${DT}\"}" -H "Content-Type: application/json"
  done
fi



