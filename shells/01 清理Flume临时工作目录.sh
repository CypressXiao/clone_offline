#!/bin/bash
echo  "清理本地 flume采集数据生成的工作文件  (偏移量)"
rm -rf  /opt/flume_data/
echo  "清理HDFS上存储数据的目标测试路径...."
hdfs dfs  -rm  -r  /doe