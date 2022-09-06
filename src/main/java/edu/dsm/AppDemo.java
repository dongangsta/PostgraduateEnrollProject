package edu.dsm;

import edu.dsm.config.DruidConfig;
import edu.dsm.config.SwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("edu.dsm.dao")
@EnableAsync
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class}) //排除mongo的自动配置
public class AppDemo {
    /**
     * 启动类
     * Swagger配置类：{@link SwaggerConfig}
     * Druid配置类：{@link DruidConfig}
     * 热部署:ctrl+f9
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AppDemo.class);
    }
}
