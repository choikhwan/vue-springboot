package com.epas.login.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.epas.login.interceptor.LoggerInterceptor;
import com.epas.login.interceptor.LoginCheckInterceptor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**/*.do")
                .excludePathPatterns("/log*");
    }
	
	@Value("file://172.16.1.7/")
	//@Value("file:///D:/")
	private String resourcePath;
	
	@Value("/imgfile/**")
	private String imagePath;
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		        .addResourceLocations("classpath:/templates/", "classpath:/static/");
		
		registry.addResourceHandler(imagePath)
        		.addResourceLocations(resourcePath);
	}

}
