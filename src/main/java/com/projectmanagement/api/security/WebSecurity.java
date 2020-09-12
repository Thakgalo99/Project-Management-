package com.projectmanagement.api.security;

import com.projectmanagement.api.services.IUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final IUserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncode;

    public WebSecurity(IUserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncode){
        this.userDetailsService=userDetailsService;
        this.bCryptPasswordEncode=bCryptPasswordEncode;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //.addFilter(new AuthenticationFilter(authenticationManager()));
    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncode);
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL);
        web.ignoring().antMatchers(SecurityConstants.AUTH_WHITELIST);
    }
}
