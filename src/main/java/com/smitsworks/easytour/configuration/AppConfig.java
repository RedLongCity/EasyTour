package com.smitsworks.easytour.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author redlodlongcity
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.smitsworks.easytour")
public class AppConfig extends WebMvcConfigurerAdapter {

}
