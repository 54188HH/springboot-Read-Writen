package com.lzq.springbootredis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot-redis
 * @description:
 * @author: liuzhenqi
 * @create: 2020-04-20 10:29
 **/
@Configuration
@Component
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.lzq-master")
    public DataSource lzqMasterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.druid.lzq-slave")
    public DataSource lzqSlaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource lzqMasterDataSource,DataSource lzqSlaveDataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("lzq-master",lzqMasterDataSource);
        targetDataSources.put("lzq-slave", lzqSlaveDataSource);
        return new DynamicDataSource(lzqMasterDataSource, targetDataSources);
    }
}
