package com.oksana.library.config;

import com.oksana.library.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;

@Configuration
public class GeneralConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder result = new Jackson2ObjectMapperBuilder();
        result.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return result;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new UserAuditorAwareImpl();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
