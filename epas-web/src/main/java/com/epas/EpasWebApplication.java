package com.epas;

import java.io.IOException;
import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * This method calculates the sum of two numbers.

 * @since 2023. 5. 24.
 * @author steam_dev01
 * @see
 * 
 *      <pre>
 *  Class Name : EpasApplication.java
 *  Description : 
 *  Information :
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023. 5. 24.      steam_dev01        initial
 *
 *  Copyright (C) 2023 by HTK All right reserved.
 *      </pre>
 */
@SpringBootApplication
public class EpasWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(EpasWebApplication.class, args);
  }

  @Bean
  MappingJackson2JsonView jsonView() {
    return new MappingJackson2JsonView();
  }

  @Bean
  public MessageSource messageSource() throws IOException {

    Locale.setDefault(Locale.ROOT);
    var messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:/message/message");
    messageSource.setDefaultLocale(new Locale("en"));
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(3);

    return messageSource;
  }

  @Bean
  public MessageSourceAccessor messageSourceAccessor() throws IOException {
    return new MessageSourceAccessor(messageSource());
  }

  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(new Locale("en"));
    return sessionLocaleResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    return interceptor;
  }

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}