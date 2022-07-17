package cn.doitedu.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 22.7.17
 * @Created by HANGGE
 * @Description
 */
public class TestDateFormat {
    public static void main(String[] args) {
        long ts = 1657955995935l ;
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String str = format.format(new Date(ts));
        System.out.println(str);
    }
}
