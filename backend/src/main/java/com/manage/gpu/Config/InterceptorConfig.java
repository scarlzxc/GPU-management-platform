package com.manage.gpu.Config;
import com.manage.gpu.interceptor.AdminInterceptor;
import com.manage.gpu.interceptor.TeacherInterceptor;
import com.manage.gpu.interceptor.StudentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tcm
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor getStudentInterceptor(){
        return new StudentInterceptor();
    }
    @Bean
    public HandlerInterceptor getteacherInterceptor(){
        return new TeacherInterceptor();
    }
    @Bean
    public HandlerInterceptor getAdminInInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getteacherInterceptor())
                //不放行的接口;通常所有不放行
                .addPathPatterns("/teacher/**")
                //放行，
                .excludePathPatterns("/teacher/login");
        registry.addInterceptor(getStudentInterceptor())
                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/login");
        registry.addInterceptor(getAdminInInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:C:/temp-seat/");
    }

}

