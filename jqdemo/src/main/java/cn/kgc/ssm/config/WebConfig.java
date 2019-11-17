package cn.kgc.ssm.config;

import cn.kgc.ssm.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/*")
        .excludePathPatterns("/admin/index.html","/admin/*.js","/admin/*.css");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
