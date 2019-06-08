package com.oksana.library.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
