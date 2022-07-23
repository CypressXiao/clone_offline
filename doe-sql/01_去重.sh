-- 创建一张表  外部表 external
create database if not exists  doe ;
drop table if exists doe.tmp_raw_applog ;
create external table if not exists doe.tmp_raw_applog(
    line string
) location '/doe/data/app_log/2022-07-23' ;

-- 查看加载数据的行数   4225
select count(1) from doe.tmp_raw_applog ;

-- 设置本地运行模式
set mapreduce.framework.name=local ;

-- 去重  验证
select
count(1)
from
(select  distinct line from doe.tmp_raw_applog) t
 ;

-- 将去重后的结果保存在原来的日志目录中
insert  overwrite  table  doe.tmp_raw_applog
   select  distinct line from doe.tmp_raw_applog ;
----------------------------------------------------------------
-- 使用分区表  处理每天的数据
-- 使用压缩 节省存储空间

create database if not exists  doe ;
drop table if exists doe.tmp_raw_applog ;
create external table if not exists doe.tmp_raw_applog(
    line string
)
partitioned by (dt string) ;

load data  inpath '/doe/data/app_log/2022-07-23'  into  table  doe.tmp_raw_applog partition (dt='2022-07-23') ;

-- 指定压缩
set hive.exec.compress.output = true;
  set mapred.output.compression.codec = org.apache.hadoop.io.compress.GzipCodec;

insert  overwrite  table  doe.tmp_raw_applog partition (dt='2022-07-23')
-- 设置输出压缩
   select  distinct line from doe.tmp_raw_applog  where  dt = '2022-07-23'; -- > 将结果输出到表的分区目录下  没有压缩

select count(1) from   doe.tmp_raw_applog where  dt = '2022-07-23';



