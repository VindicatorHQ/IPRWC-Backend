package com.webshop.webshopbackend.common.config;

import com.webshop.webshopbackend.common.security.filter.CustomAccessDeniedHandler;
import com.webshop.webshopbackend.common.security.filter.DelegatedAuthEntryPoint;
import com.webshop.webshopbackend.common.util.JWTFilter;
import com.webshop.webshopbackend.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final DelegatedAuthEntryPoint authEntryPoint;

    @Bean
    public DefaultSecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/user/info").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/user/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAuthority(Role.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/building/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/building/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/building/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/building/**").hasAuthority(Role.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/product/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasAuthority(Role.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/order/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/order/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/order/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/order/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/category/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/category/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/category/**").hasAuthority(Role.ADMIN.name())

                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new RequestContextFilter(), ChannelProcessingFilter.class)
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
