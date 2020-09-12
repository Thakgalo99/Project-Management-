package com.projectmanagement.api.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GlobalConfiguration {

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
        //https://github.com/modelmapper/modelmapper/issues/212
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/Images/**")
//                .addResourceLocations("classpath:/path/to/root/")
//                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic());
//    }

//    @Bean
//    public SpringApplicationContext springApplicationContext() {
//        return new SpringApplicationContext();
//    }
}
