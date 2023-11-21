package top.chen.train.common.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.chen.train.common.interceptor.MemberInterceptor;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: SpringMvcConfig
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    private MemberInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/member/hello",
                        "/member/member/send-code",
                        "/member/member/register",
                        "/member/member/login"
                );
    }
}
