package Mystudy.spring.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean ExceptionFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new ExceptionFilter());  // 추가하려는 필터
        filterFilterRegistrationBean.setOrder(1); // Filter 순서
        filterFilterRegistrationBean.addUrlPatterns("/*"); // 필터 URL 매칭 패턴

        return filterFilterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());  // 추가하려는 필터
        filterFilterRegistrationBean.setOrder(2); // Filter 순서
        filterFilterRegistrationBean.addUrlPatterns("/*"); // 필터 URL 매칭 패턴

        return filterFilterRegistrationBean;
    }
}
