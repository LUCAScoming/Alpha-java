package com.event.demo;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.logging.Logger;

@SpringBootApplication

/*@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class //禁止springboot自动加载持久化bean
})*/
public class DemoApplication {
    private static Logger logger = Logger.getLogger(String.valueOf(Application.class));

    public static void main(String[] args) {
        logger.info("=======启动开始=======");
        SpringApplication.run(DemoApplication.class, args);
        logger.info("=======启动结束=======");
    }

    /*
     * 处理跨域代码
     * */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }



}
