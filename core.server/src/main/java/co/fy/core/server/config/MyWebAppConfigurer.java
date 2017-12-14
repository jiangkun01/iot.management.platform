package co.fy.core.server.config;


import co.fy.core.server.interceptor.PageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by joker on 9/7/17.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链

        // addPathPatterns 用于添加拦截规则

        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}