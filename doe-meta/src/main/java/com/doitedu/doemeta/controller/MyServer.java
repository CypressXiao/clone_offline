package com.doitedu.doemeta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date 22.7.20
 * @Created by HANGGE
 * @Description
 */
@Controller
public class MyServer {
    /**
     * java类的方法是可以接收页面的请求的
     *     <a href="click">点击</a>
     * @return
     * 1  编写一个类 : 类上加 @Controller注解  ,标记当前类是一个控制器  , 控制器可以接收请求
     * 2 页面的请求 请求到具体的某个方法处理    方法上添加请求映射 @RequestMapping("/click")
     * 3  被请求的方法 就会执行 接收请求 返回数据
     */

    @RequestMapping("/click")
    @ResponseBody
    public  String  click(){
        System.out.println("点啥点!!");
        return "点你咋地...... !" ;
    }

}
