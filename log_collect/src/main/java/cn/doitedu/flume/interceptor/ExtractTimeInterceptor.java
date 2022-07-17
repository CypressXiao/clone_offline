package cn.doitedu.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Date 22.7.17
 * @Created by HANGGE
 * @Description  学大数据到多易教育
 * 自定义 FLume的拦截器
 *  抽取日志中的时间戳  将时间戳转换成2022-07-16格式
 *  将日期格式的数据存储在Header中  返回
 */
public class ExtractTimeInterceptor implements Interceptor {
    public ExtractTimeInterceptor() {
    }

    String  key = null ;
    SimpleDateFormat format = null ;
    public  ExtractTimeInterceptor(String  key){
        this.key = key ;
    }

    /**
     * 拦截器的初始化方法 这个方法在拦截器创建的时候执行一次
     */
    public void initialize() {
         format =  new SimpleDateFormat("YYYY-MM-dd");
    }

    /**
     * 拦截住每个事件(每条数据)
     * 1 抽取时间
     * 2 格式转换
     * 3 将时间存储在Header中
     * 4 返回
     * @param event
     * @return
     */
    public Event intercept(Event event) {
        try {
            // 解析event 获取日志数据
            byte[] body = event.getBody();
            String line = new String(body);
            // 从日志数据中获取时间戳
            JSONObject jsonObject = JSON.parseObject(line);
            Long ts = jsonObject.getLong("timeStamp");
            // 格式转换
            String dt = format.format(new Date(ts));
            // 获取Header  %{log_dt}
            event.getHeaders().put(key , dt) ;
            // put数据到Header中
            return event;
        } catch (Exception e) {
            /**
             * 如果解析异常就等于拦截器没有生效 ,不会对内部数据做任何处理
             */
            return event;
        }
    }
    /**
     * 处理所有的事件
     * @param events
     * @return
     */
    public List<Event> intercept(List<Event> events) {
        // 遍历一批所有的时间  处理 添加时间头信息
        for (Event event : events) {
            intercept(event) ;
        }
        return events;
    }

    /**
     * 拦截器的结束的方法  处理完拦截业务以后 会执行一次
     */
    public void close() {
        format = null ;
    }

    // 拦截器构建器   注意 一定用  static  class
    public  static  class  Builder implements   Interceptor.Builder{
        String dt = null ;
        public Interceptor build() {
            return new  ExtractTimeInterceptor(dt);  // log_dt
        }
        /**
         * 在创建拦截器之前  获取用户自定义的参数
         * @param context
         */
        public void configure(Context context) {
            //a1.sources.s1.interceptors.i1.x = log_dt
             dt = context.getString("x");  //
        }
    }
}
