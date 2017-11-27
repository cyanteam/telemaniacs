package org.cyanteam.telemaniacs.core;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceContextConfiguration.class)
@ComponentScan(basePackages = "org.cyanteam.telemaniacs.core")
public class ServiceContextConfiguration extends Throwable {
    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.create()
                .withMappingFiles("mappings.xml")
                .build();
    }
}
