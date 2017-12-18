package org.cyanteam.telemaniacs.demo;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(ServiceContextConfiguration.class)
@ComponentScan(basePackages = "org.cyanteam.telemaniacs.demo")
public class DemoContextConfiguration {
    @Autowired
    private DemoDataLoader demoDataLoader;

    @PostConstruct
    public void loadDemoData() {
        demoDataLoader.load();
    }
}
