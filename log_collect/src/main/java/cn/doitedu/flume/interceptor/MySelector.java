package cn.doitedu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Random;

/**
 * @Date 22.7.19
 * @Created by HANGGE
 * @Description
 * 自定义选择器  本质就是拦截器
 */
public class MySelector implements Interceptor {
    Random random  = null ;
    @Override
    public void initialize() {
        random = new Random();
    }

    /**
     * 给每条Event事件  添加头信息  state  作为数据选择channel的依据
     *  0  c1
     *  1  c2
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        event.getHeaders().put("state" , random.nextInt(2)+"") ;
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event) ;
        }
        return list;
    }

    @Override
    public void close() {

    }

    public   static class  SelectorBuilder  implements  Interceptor.Builder{


        @Override
        public Interceptor build() {
            return new MySelector();
        }

        @Override
        public void configure(Context context) {

        }
    }}
