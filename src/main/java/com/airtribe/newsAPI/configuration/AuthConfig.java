package com.airtribe.newsAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            authorizeRequests -> authorizeRequests.requestMatchers("/api/register","/api/dashboard", "/h2-conosle/**", "/api/login", "/api/tokenDashboard", "/api/preferences", "/api/news")
                            .permitAll()
                            .anyRequest().authenticated())
                    .formLogin(formLogin -> formLogin.defaultSuccessUrl("/api/dashboard", true).permitAll())
                    .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

            return httpSecurity.build();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
