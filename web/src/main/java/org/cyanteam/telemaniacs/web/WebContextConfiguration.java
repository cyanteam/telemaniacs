package org.cyanteam.telemaniacs.web;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.demo.DemoContextConfiguration;
import org.cyanteam.telemaniacs.demo.DemoDataLoader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({DemoContextConfiguration.class, ServiceContextConfiguration.class })
@ComponentScan(basePackages = { "org.cyanteam.telemaniacs.rest" })
@EnableWebMvc
public class WebContextConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
