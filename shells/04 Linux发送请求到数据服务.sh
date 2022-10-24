#!/bin/bash
# 在linux上发送网络请求 , 请求我们自己的服务器
# 请求服务的时候携带请求参数 , 请求参有点多 ,将请求参数统一的由bean接收
# curl  服务地址   参数(json)    后台可以接收json格式的数据
# 指定传递的参数是 json  设置 请求头 -H  "Content-Type: application/json"
# 请求方式  GET请求  POST请求
# GET请求将请求数据写在地址栏中  比如:http://doitedu01:8080/click?name=zss&pwd=!@3qwe
# POST请求 请求数据不再地址栏 在请求体中  安全 ,大 , 支持上传下载
# 请求参数很多 使用POST  没有请求参数 或者1,2个使用Get
# -H 对请求头进行补充设置  比如请求数据类型  比如编码集
# -X 指定请求方式  -X  POST
#----------------------------------------------------------------
#  请求的服务地址  http://windows:8081/click2
#  请求的参数: serverName  logType  logCount
###############################################
curl  http://windows:8080/click2  -X POST   -d"{\"serverName\":\"doitedu01\" , \"logType\":\"app_log\" , \"logCount\":\"20000\"}" -H "Content-Type: application/json"