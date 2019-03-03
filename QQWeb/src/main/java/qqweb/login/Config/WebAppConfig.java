package qqweb.login.Config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import qqweb.login.interceptor.SessionInterceptor;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/*").excludePathPatterns("/index")
        .excludePathPatterns("/index2").excludePathPatterns("/index_1").excludePathPatterns("/index_2").excludePathPatterns("/index_3").excludePathPatterns("/login")
        .excludePathPatterns("/login2").excludePathPatterns("/change").excludePathPatterns("/register").excludePathPatterns("/update");
    }
}
