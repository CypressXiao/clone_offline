package com.doitedu.doemeta.controller;

import com.doitedu.doemeta.beans.RequestBean;
import com.doitedu.doemeta.dao.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: HANGGE
 * @qq: 598196583
 * @Tips: 学大数据 ,到多易教育
 * @Description:
 * 接收请求
 * 接收请求参数
 * 处理请求
 *    判断业务
 *    操作数据库  JDBC
 * 返回结果
 */
@Controller
@RequestMapping("/api")
public class MyServer {
    /**
     * 自动注入  MyMapper的代理实现类
     */
    @Autowired
    MyMapper mapper ;

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
    @RequestMapping("/report")
    @ResponseBody
    public  RequestBean  report(@RequestBody RequestBean bean){
        System.out.println("接收到的数据:"+ bean);
        /**
         * 接收到请求参数
         * 将数据存储到MySQL库
         */
        mapper.addRequestBean(bean); // 实际是实现类
        return bean ;
    }

    /**
     * 提供服务
     *   根据日期和日志类型获取日志总行数
     */
    @RequestMapping("/getlogLines")
    @ResponseBody
    public  Long  getlogLines(@RequestBody RequestBean requestBean){
        System.out.println(requestBean);
        return  mapper.getLinesByDTAndLogType(requestBean).get(0);
    }


    /**
     * 提供服务
     *   根据日期和日志类型获取HDFS日志总行数
     */
    @RequestMapping("/getHdfsLogLines")
    @ResponseBody
    public  Long  getHdfsLogLines(@RequestBody RequestBean requestBean){
        return  mapper.getLinesByDTAndLogTypeWithHDFS(requestBean);
    }


}
