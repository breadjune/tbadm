package kr.co.trafficboss.tbadm.config;

import kr.co.trafficboss.tbadm.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String RESOURCES = "/static/**";
    private static final String LOGIN = "/login";

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns(RESOURCES)
                .excludePathPatterns(LOGIN);
    }
}
