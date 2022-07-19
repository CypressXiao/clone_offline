#!/bin/bash
# 清理集群上每台节点上的flume环境
for hostName  in  doitedu01  doitedu02 doitedu03
do
   echo  " ${hostName} : 清理 $1 "
   ssh ${hostName}    "rm -rf $1 ; exit"
   # 判断清理工作是否成功  $?上个命令是否执行成功  如果成功 返回 0 否则返回非0 数字
   # 数字是否相等   ==   -eq  -gt -ge -lt  -le  -ne
   if [ $? -eq 0 ]; then
        echo  "清理${hostName} 的 $1 删除完毕......."
     else
        echo  "清理${hostName} 的  $1 删除失败......."
   fi
done
