/*
 * package com.cde.ims.application.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * public class SecurityConfiguration { extends WebSecurityConfigurerAdapter{
 * 
 * private static final String[] AUTH_LIST = { // "/v2/api-docs", //
 * "/api/v1/*", "/configuration/ui", // "/swagger-resources/*", //
 * "/configuration/security", // "/swagger-ui.html", // "/webjars/**a" // };
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception {
 * auth.inMemoryAuthentication().withUser("user").password("password").roles(
 * "USER").and().withUser("admin").password(passwordEncoder().encode("admin")).
 * roles("USER", "ADMIN"); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers(AUTH_LIST).authenticated().and().
 * httpBasic(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * 
 * }
 */