package com.doitedu.doemeta.beans;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

/**
 * @Date: 22.7.22
 * @Author: HANGGE
 * @qq: 598196583
 * @Tips: 学大数据 ,到多易教育
 * @Description:接收请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBean {
    /** 服务器名*/
    private  String  serverName ;
    /** 日志类型*/
    private  String  logType ;
    /** 日志行数*/
    private  Long  logCount ;
    /**日期*/
    private  String  dt ;
}
