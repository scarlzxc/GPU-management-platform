package com.manage.gpu.Config;
import com.manage.gpu.interceptor.AdminInterceptor;
import com.manage.gpu.interceptor.TeacherInterceptor;
import com.manage.gpu.interceptor.StudentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tcm
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TeacherInterceptor())
                //不放行的接口;通常所有不放行
                .addPathPatterns("/teacher/**")
                //放行，
                .excludePathPatterns("/teacher/login");
        registry.addInterceptor(new StudentInterceptor())
                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/login");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:C:/temp-seat/");
    }

}

