package com.example.config;

import com.example.interceptor.AnnotationBasedInterceptor;
import com.example.interceptor.BaggageInterceptor;
import com.example.interceptor.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;

    @Autowired
    private AnnotationBasedInterceptor annotationBasedInterceptor;

    @Autowired
    private BaggageInterceptor baggageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor)
                .addPathPatterns("/api/**"); // Interceptor 1

        registry.addInterceptor(annotationBasedInterceptor)
                .addPathPatterns("/api/**"); // Interceptor 2

        registry.addInterceptor(baggageInterceptor)
                .addPathPatterns("/**"); // Interceptor 3
    }
}
