package com.ohehe.shin.conf.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author luokp on 2018/5/18.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "shin.datasource.druid_stat")
public class DruidStatProperties {

    private StatServletProperties servlet = new StatServletProperties();

    private StatFilterProperties filter = new StatFilterProperties();

    @Getter
    @Setter
    public static class StatFilterProperties {
        private String url = "/*";
        private String exclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";
    }

    @Getter
    @Setter
    public static class StatServletProperties {
        private String url = "/druid/*";
        private boolean resetEnable = true;
        private String loginUsername = "root";
        private String loginPassword = "root";
    }

}
