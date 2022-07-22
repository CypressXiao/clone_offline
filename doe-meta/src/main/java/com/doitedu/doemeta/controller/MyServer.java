package com.doitedu.doemeta.controller;

import com.doitedu.doemeta.beans.RequestBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: HANGGE
 * @qq: 598196583
 * @Tips: 学大数据 ,到多易教育
 * @Description:
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
     * 4 接收页面的请求参数
     *
     *--------------------------------------------------
     * 在linux01上 请求 这个方法携带请求参数  logType  cnt
     *      提示: curl
     */

    @RequestMapping("/click")
    @ResponseBody
    public  String  click(int id , String  name){
        System.out.println("点啥点!!");
        return "点你咋地...... !"+ name ;
    }

    /**
     * 可以使用方法的参数接收请求数据的 如果请求数据很多  参数很多
     * 使用JaveBean接收请求参数  方便
     * @RequestBody
     *    可以直接接收json请求数据格式
     *   使用Bean接收数据 ,以请求对应的参数  和 类的数据对应接收
     * @return
     */
    @RequestMapping("/click2")
    @ResponseBody
    public  RequestBean  click2(@RequestBody RequestBean bean){
        System.out.println("接收到的数据:"+ bean);
        /**
         * 接收到请求参数
         * 将数据存储到MySQL库
         */
        return bean ;
    }




}
