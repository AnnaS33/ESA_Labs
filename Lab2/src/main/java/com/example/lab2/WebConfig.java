package com.example.lab2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        CorsRegistration corsRegistration= corsRegistry.addMapping("/**");
        corsRegistration.allowedOrigins("http://localhost:8081");
        corsRegistration.allowedMethods("*");

    }
    @Bean
    public XsltViewResolver xsltViewResolver(){
        XsltViewResolver xsltViewResolver=new XsltViewResolver();
        xsltViewResolver.setPrefix("classpath:/xslt/");
        xsltViewResolver.setSuffix(".xslt");
        return xsltViewResolver;
    }

}
