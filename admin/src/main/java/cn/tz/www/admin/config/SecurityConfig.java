package cn.tz.www.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import cn.tz.www.customer.service.SecurityUserDetailsService;


/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    //auth.userDetailsService(securityUserDetailsService()).passwordEncoder(passwordEncoder);
		  auth
	      .inMemoryAuthentication()
	          .withUser("admin").password("123456").roles("USER");
	    
	    
	  }
	  @Bean
	  UserDetailsService securityUserDetailsService(){
		  return new SecurityUserDetailsService();
	  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/resources/**",
        "/webjars/**",
        "/js/**",
        "/images/**",
        "/css/**",
        "/h2-console/*",
        "/assets/*"
    );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {  http
      .authorizeRequests()
      .antMatchers("/", "/home").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/index")
      .failureUrl("/login?error")
      .permitAll()
      .and()
      .logout()
      .permitAll();}


}