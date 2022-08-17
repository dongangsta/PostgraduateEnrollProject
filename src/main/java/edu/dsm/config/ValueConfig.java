package edu.dsm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring框架注解@Value的配置类
 */
@Configuration
@PropertySource({"classpath:author.properties"})
public class ValueConfig {

}
