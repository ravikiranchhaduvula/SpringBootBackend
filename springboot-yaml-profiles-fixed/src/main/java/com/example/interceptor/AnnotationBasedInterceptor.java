package com.example.interceptor;

import com.example.annotation.TrackRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AnnotationBasedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if method or class has @TrackRequest
            boolean hasAnnotation = handlerMethod.hasMethodAnnotation(TrackRequest.class) ||
                    handlerMethod.getBeanType().isAnnotationPresent(TrackRequest.class);

            if (hasAnnotation) {
                System.out.println("📍[TrackRequest] Intercepted annotated handler: " + request.getRequestURI());
            }
        }

        return true;
    }
}

