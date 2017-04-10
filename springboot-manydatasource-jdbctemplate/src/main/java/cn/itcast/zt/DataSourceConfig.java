package cn.itcast.zt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 多数据源
 * Created by zhangtian on 2017/4/10.
 */
@Configuration
public class DataSourceConfig {
    /**
     * 主数据源
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Qualifier(value = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build() ;
    }

    /**
     * 第二数据源
     * @return
     */
    @Bean(name = "secondaryDataSource")
    @Qualifier(value = "secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build() ;
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier(value = "primaryDataSource") DataSource dataSource) {
        return  new JdbcTemplate(dataSource) ;
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier(value = "secondaryDataSource") DataSource dataSource) {
        return  new JdbcTemplate(dataSource) ;
    }
}
