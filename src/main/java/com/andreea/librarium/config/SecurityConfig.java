//package com.andreea.librarium.config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/register", "/css/**", "/js/**").permitAll()
//                .antMatchers("/bibliotecar_pagina_principala").hasAuthority("BIBLIOTECAR") // Adjust your role here
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/homepage", true)
//                .and()
//                .logout()
//                .permitAll();
//
//        return http.build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.core.convert.ConversionService;
////import org.springframework.format.FormatterRegistry;
////import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
////import org.springframework.format.support.FormattingConversionServiceFactoryBean;
////import org.springframework.web.servlet.config.annotation.EnableWebMvc;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////
////import java.time.Instant;
////import java.time.format.DateTimeFormatter;
////
////@Configuration
////@EnableWebMvc
////public class SecurityConfig implements WebMvcConfigurer {
////
////    @Bean
////    public ConversionService conversionService() {
////        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
////
////        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
////        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
////        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
////        registrar.registerFormatters((FormatterRegistry) factoryBean);
////
////        return factoryBean.getObject();
////    }
////
////    @Override
////    public void addFormatters(FormatterRegistry registry) {
////        registry.addConverter(String.class, Instant.class, source -> Instant.parse((String) source));
////    }
////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
//////
//////
//////import com.andreea.librarium.security.CustomAuthenticationProvider;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.authentication.BadCredentialsException;
//////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//////import org.springframework.security.core.Authentication;
//////import org.springframework.security.core.AuthenticationException;
//////import org.springframework.security.core.userdetails.UserDetails;
//////import org.springframework.security.core.userdetails.UserDetailsService;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//////
//////@EnableWebSecurity
//////public class SecurityConfig extends WebSecurityConfigurerAdapter {
//////
//////    @Autowired
//////    private CustomAuthenticationProvider customAuthenticationProvider;
//////
//////    @Override
//////    protected void configure(HttpSecurity http) throws Exception {
//////        // Your security configuration here
//////    }
//////
//////    @Override
//////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//////        auth.authenticationProvider(customAuthenticationProvider);
//////    }
//////}
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
////////
////////
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.context.annotation.Bean;
////////import org.springframework.context.annotation.Configuration;
////////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////////import org.springframework.security.core.userdetails.UserDetailsService;
////////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////////import org.springframework.security.web.SecurityFilterChain;
////////import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
////////@Configuration
////////@EnableWebSecurity
////////public class SecurityConfig   {
////////    @Autowired
////////    private UserDetailsService userDetailsService;
////////    @Bean
////////    public static BCryptPasswordEncoder passwordEncoder() {
////////
////////        return new BCryptPasswordEncoder();
////////    }
////////@Bean
////////public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////////    http.csrf(csrf -> csrf.disable())
////////            .authorizeHttpRequests((authorize) ->
////////                    authorize.antMatchers("/register/**").permitAll()
////////                            .antMatchers("/index").permitAll()
////////                            .antMatchers("/users").hasRole("ADMIN")
////////            ).formLogin(
////////                    form -> form
////////                            .loginPage("/login")
////////                            .loginProcessingUrl("/login")
////////                            .defaultSuccessUrl("/users")
////////                            .permitAll()
////////            ).logout(
////////                    logout -> logout
////////                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////////                            .permitAll()
////////            );
////////    return http.build();
////////}
////////
////////    @Autowired
////////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////////        auth
////////                .userDetailsService(userDetailsService)
////////                .passwordEncoder(passwordEncoder());
////////    }
////////}
////////
