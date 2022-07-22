#!/bin/bash
#  每间隔 10s中 发送一次网络请求 , 请求外部的一个url地址
while [  1 == 1 ]; do
   $(curl https://hangge.blog.csdn.net/article/details/118271965) 1>/dev/null 2>&1  &
    # 休眠10s
    sleep  10
done
