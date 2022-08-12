package com.teckmils.warehousemanagementsystem.security;

import com.teckmils.warehousemanagementsystem.domain.user.UserRole;
import com.teckmils.warehousemanagementsystem.domain.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private UserDetailsService userDetailsService;

    public SecurityConfig(final UserService userService) {
        this.userDetailsService = userService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws
            Exception {
        http
//            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-ui/index.html").permitAll()
            .antMatchers("/v3/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/**").authenticated()
//            .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(UserRole.ADMIN)
//            .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(UserRole.ADMIN)
//            .antMatchers(HttpMethod.PATCH, "/api/**").hasAuthority(UserRole.ADMIN)
//            .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(UserRole.ADMIN)
        ;

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }
}
