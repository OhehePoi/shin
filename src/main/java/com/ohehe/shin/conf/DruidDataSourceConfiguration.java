package com.ohehe.shin.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.ohehe.shin.conf.properties.DruidDataSourceProperties;
import com.ohehe.shin.conf.properties.DruidStatProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid数据源配置
 *
 * @author luokp on 2018/5/18.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class, DruidStatProperties.class})
public class DruidDataSourceConfiguration {

    @Bean
    public DataSource dataSource(DruidDataSourceProperties druidDataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(druidDataSourceProperties.getUrl());
        dataSource.setUsername(druidDataSourceProperties.getUsername());
        dataSource.setPassword(druidDataSourceProperties.getPassword());
        dataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        dataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
        dataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
        dataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
        dataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
        dataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
        dataSource.setUseGlobalDataSourceStat(druidDataSourceProperties.isUseGlobalDataSourceStat());

        try {
            dataSource.setFilters(druidDataSourceProperties.getFilters());
        } catch (SQLException e) {
            log.error("druid datasource configuration initialization filters error!", e);
        }

        return dataSource;
    }

    @Configuration
    @ConditionalOnWebApplication
    public static class DruidStatConfiguration {

        @Bean
        public ServletRegistrationBean servletRegistrationBean(DruidStatProperties druidStatProperties) {
            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());
            if (StringUtils.isNotBlank(druidStatProperties.getServlet().getUrl())) {
                servletRegistrationBean.addUrlMappings(druidStatProperties.getServlet().getUrl());
            }
            if (StringUtils.isNotBlank(druidStatProperties.getServlet().getLoginUsername()) && StringUtils.isNotBlank(druidStatProperties.getServlet().getLoginPassword())) {
                servletRegistrationBean.addInitParameter("loginUsername", druidStatProperties.getServlet().getLoginUsername());
                servletRegistrationBean.addInitParameter("loginPassword", druidStatProperties.getServlet().getLoginPassword());
            }
            servletRegistrationBean.addInitParameter("resetEnable", String.valueOf(druidStatProperties.getServlet().isResetEnable()));
            return servletRegistrationBean;
        }

        @Bean
        public FilterRegistrationBean filterRegistrationBean(DruidStatProperties druidStatProperties) {
            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
            if (StringUtils.isNotBlank(druidStatProperties.getFilter().getUrl())) {
                filterRegistrationBean.addUrlPatterns(druidStatProperties.getFilter().getUrl());
            }
            if (StringUtils.isNotBlank(druidStatProperties.getFilter().getExclusions())) {
                filterRegistrationBean.addInitParameter("exclusions", druidStatProperties.getFilter().getExclusions());
            }
            return filterRegistrationBean;
        }

    }

}
