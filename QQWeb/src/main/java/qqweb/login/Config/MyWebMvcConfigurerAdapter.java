package qqweb.login.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //指向外部目录
        registry.addResourceHandler("img//**").addResourceLocations("file:F:/大学用/东旭/java/作业/寒假作业/git上传/QQWeb/src/main/resources/static/img/");
        super.addResourceHandlers(registry);
    }
}
