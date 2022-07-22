package com.doitedu.doemeta.dao;

import com.doitedu.doemeta.beans.RequestBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 22.7.22
 * @Author: HANGGE
 * @qq: 598196583
 * @Tips: 学大数据 ,到多易教育
 * @Description:
 * 接口, 持久层
 * 代理模式
 *  mybatis将 接口和接口上的sql语句  自动封装成 实现类的实现方法
 *    方法
 *    将方法的参数中的属性值  通过 #{取出}  对SQL语句预编译
 *    使用sql语句去操作数据库
 */
@Mapper
public interface MyMapper {
    @Insert("insert  into tb_log_line values(#{serverName} , #{logType} , #{logCount} , #{dt})")
    public void  addRequestBean(RequestBean bean) ;

    @Select("select\n" +
            "sum(log_count)\n" +
            "FROM\n" +
            "tb_log_line \n" +
            "where servername !='HDFS' and  dt = #{dt}\n" +
            "GROUP BY dt , log_type \n" +
            "having log_type = #{logType}")
    public List<Long> getLinesByDTAndLogType(RequestBean requestBean);

    @Select("select\n" +
            "log_count\n" +
            "FROM\n" +
            "tb_log_line \n" +
            "where servername ='HDFS' and  dt = #{dt}")
    public  Long  getLinesByDTAndLogTypeWithHDFS(RequestBean requestBean);
}
