package com.ohehe.shin.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luokp on 2018/5/18.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "shin.datasource.druid")
public class DruidDataSourceProperties {

    /**
     * 连接数据库的url
     */
    private String url;

    /**
     * 连接数据库的用户名
     */
    private String username;

    /**
     * 连接数据库的密码
     */
    private String password;

    /**
     * 数据库驱动类名
     */
    private String driverClassName;

    /**
     * 初始化连接数
     */
    private int initialSize;

    /**
     * 最小连接数
     */
    private int minIdle;

    /**
     * 最大连接数
     */
    private int maxActive;

    /**
     * 获取连接等待超时时间
     */
    private int maxWait;

    /**
     * 检测间隔
     */
    private int timeBetweenEvictionRunsMillis;

    /**
     * 最小生存时间
     */
    private int minEvictableIdleTimeMillis;

    /**
     * 检测sql
     */
    private String validationQuery;

    /**
     * 空闲时间大于检测间隔时是否进行检测
     */
    private boolean testWhileIdle;

    /**
     * 申请连接时是否检测
     */
    private boolean testOnBorrow;

    /**
     * 归还连接时是否执行检测
     */
    private boolean testOnReturn;

    /**
     * 是否启用PSCache
     */
    private boolean poolPreparedStatements;

    /**
     * 每个连接上PSCache的大小
     */
    private int maxPoolPreparedStatementPerConnectionSize;

    /**
     * 监控统计拦截的filter
     */
    private String filters;

    /**
     * 是否合并多个DruidDataSource的监控数据
     */
    private boolean useGlobalDataSourceStat;

}
