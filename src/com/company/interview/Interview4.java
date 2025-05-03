package com.company.interview;

public class Interview4 {
    // 实现表数据行转列
    // type value
    //  A    90
    //  B    100
    //  C    80
    // 一行 A 90
    // select Max(case when type = 'A' then value else null end),
    // Max(case when type = 'B' then value else null end),
    // Max(case when type = 'C' then value else null end) FROM table where w
    // 动态处理sql 客户单号 %
    // 业务查询多表联合查询 十几张表查询 5s内 处理完业务 立刻查询数据
    // 新建宽表 binlog
    // 数据流计算

}
