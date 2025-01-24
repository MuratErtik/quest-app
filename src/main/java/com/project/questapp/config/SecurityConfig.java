package com.project.questapp.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.questapp.security.JwtAuthenticationEntryPoint;
import com.project.questapp.security.JwtAuthenticationFilter;
import com.project.questapp.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//Spring Security özelliklerini etkinleştirir.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl,JwtAuthenticationEntryPoint handler){
        this.userDetailsService=userDetailsServiceImpl;
        this.handler=handler;
    }

    @Bean
    //Spring, bu metodu çağır ve dönen nesneyi sen yönet
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    // özel bir doğrulama mantığı tanımlamak icin. manuel kullanmak icin
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsFilter corsFilter(){
        //CORS, bir web sayfasının başka bir alan adından (dış kaynaktan) gelen kaynaklara erişmesine izin verir.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        //addAllowedHeader("*"), tüm HTTP başlıklarının (örneğin, Content-Type, Authorization) kabul edilmesini sağlar.
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        //bu ayarların backend'deki tüm endpoint'ler için geçerli olduğunu ifade eder.
        return new CorsFilter(source);

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors()
            .and()
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(handler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().anyRequest().permitAll().anyRequest().authenticated();

        httpSecurity.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter());            
            
    }


}
