#!/bin/bash
# 清理集群上每台节点上的flume环境
for hostName  in  doitedu01  doitedu02 doitedu03
do
   echo  "清理${hostName} flume采集数据生成的工作文件  (偏移量)"
   ssh ${hostName}    "rm -rf  /opt/flume_data/ ; exit"
   # 判断清理工作是否成功  $?上个命令是否执行成功  如果成功 返回 0 否则返回非0 数字
   if [ $? -e 0  ]; then
        echo  "清理${hostName} flume采集数据生成的工作文件完毕......."
     else
        echo  "清理${hostName} flume采集数据生成的工作文件失败......."
   fi
done
echo  "清理HDFS上存储数据的目标测试路径...."
hdfs dfs  -rm  -r  /doe