package com.examplesqlfour.demo.Configuration;

import java.util.Properties;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration

public class MyBatisConfiguration {
    /*@Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.examplesqlfour.demo.model.*");//实体类路径

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Configuration
    @AutoConfigureAfter(MyBatisConfiguration.class)
    public static class MyBatisMapperScannerConfigurer {

        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurers = new MapperScannerConfigurer();
            mapperScannerConfigurers.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
            mapperScannerConfigurers.setBasePackage("com.examplesqlfour.demo.dao.*");
            //配置通用mappers
            Properties properties = new Properties();
            properties.setProperty("mappers", "com.examplesqlfour.demo.common.MyMapper");
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");
            mapperScannerConfigurers.setProperties(properties);

            return mapperScannerConfigurers;
        }
            }
*/
    @Bean
    public PageHelper pageHelper() {

        System.out.println("MyBatisConfiguration.pageHelper()");

        PageHelper pageHelper = new PageHelper();

        Properties p = new Properties();

        p.setProperty("offsetAsPageNum", "true");

        p.setProperty("rowBoundsWithCount", "true");

        p.setProperty("reasonable", "true");

        pageHelper.setProperties(p);

        return pageHelper;

    }



}
