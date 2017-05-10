package cn.itcast.zt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * springboot跨域
 * springboot跨域  CORS+JWT CORS处理跨域请求，
 * JWT传输token来处理用户认证和替代cookie,使用JWT来保证API的安全
 * Spring 可以支持局部cors就是注解到具体的某个方法上@CrossOrigin
 * Created by zhangtian on 2017/4/18.
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedOrigin("http://localhost"); // 1
        corsConfiguration.addAllowedOrigin("http://cas01.example.org");//添加多个允许跨域访问的列表
        // 2 可以限制http headers，请求中不满足这个里面的要求也会出现跨域错误，
        // 常用的比如禁止下载文件，就在这里面不添加文件img，css和JavaScript的头，请求就会被过滤掉
        corsConfiguration.addAllowedHeader("*"); // 2
        // corsConfiguration.addAllowedMethod("*"); // 3
        corsConfiguration.addAllowedMethod(HttpMethod.GET); // 3
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS); // 添加可以访问的方法

        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    /**
     * 解决跨域问题
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource() ;
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source) ;
    }
}
