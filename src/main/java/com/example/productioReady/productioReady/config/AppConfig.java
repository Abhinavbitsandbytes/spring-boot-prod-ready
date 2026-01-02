package com.example.productioReady.productioReady.config;

import com.example.productioReady.productioReady.Auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl") // enabling JPA Auditing feature. Here we are specifying
// the bean name "getAuditorAwareImpl" which provides the implementation of AuditorAware interface
public class AppConfig {

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }


// we are defining a bean for AuditorAware to enable JPA
// Auditing feature. The bean name is getAuditorAwareImpl
// and it returns an instance of AuditorAwareImpl class
// which implements AuditorAware interface. This implementation
// provides the current auditor (user) information for auditing purposes.
    @Bean
    AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImpl();
    }
}
