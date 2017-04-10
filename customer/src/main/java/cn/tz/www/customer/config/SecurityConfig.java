package cn.tz.www.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.activation.DataSource;

/**
 * Created by zzc on 11/11/2016.
 */

@EnableWebSecurity
public class SecurityConfig {
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  //调用cn.gaiasys.retail.core.service.SecurityUserDetailsService(实现了UserDetailsService接口)
  @Autowired
  private UserDetailsService securityUserDetailsService;
/**
 * 
 * 验证登录用户
 * */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder);
    
    
    
  }
/**
 * 
 * 接口Url请求过滤定义
 * 
 * */
  @Configuration
  @Order(1)
  public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private javax.sql.DataSource dataSource;

    protected void configure(HttpSecurity http) throws Exception {
      http.rememberMe().rememberMeServices(rememberMeServices())
          .userDetailsService(securityUserDetailsService)
      .alwaysRemember(true);
      http
          .csrf().disable()
          .antMatcher("/api/**")
          .authorizeRequests()
          .antMatchers("/api/customer/check").permitAll()
          .antMatchers("/api/customer/login").permitAll()
          .antMatchers("/api/customer/regist").permitAll()
          //公共资源不需要认证
          .antMatchers("/api/customer/public/*").permitAll()
          .antMatchers("/api/customer/*").authenticated()
          .antMatchers("/api/customer/vip/*").hasAnyRole("VIP")
          //.antMatchers("/api/customer/vip/*").hasAnyAuthority(authorities);
          //.antMatchers("/api/customer/*").hasAnyRole("SHOP_DEV","PROV_DEV","COMMON_USER","ADMIN")
      ;
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
      JdbcTokenRepositoryImpl impl = new JdbcTokenRepositoryImpl();
      impl.setDataSource(dataSource);
      return impl;
    }

    @Bean
    public AbstractRememberMeServices rememberMeServices() {
      PersistentTokenBasedRememberMeServices rememberMeServices =
          new PersistentTokenBasedRememberMeServices("jkai8892",
              securityUserDetailsService,jdbcTokenRepository());
      rememberMeServices.setAlwaysRemember(true);
      rememberMeServices.setCookieName("remember-me");
      rememberMeServices.setTokenValiditySeconds(1209600);
      return rememberMeServices;
    }

  }

//  @Configuration
//  @Order(2)
//  public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//   
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//      web.ignoring().antMatchers(
//          "/resources/**",
//          "/webjars/**",
//          "/js/**",
//          "/images/**",
//          "/css/**",
//          "/h2-console/*",
//          "/assets/*"
//      );
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//          .antMatchers("/login*").permitAll()
//          .antMatchers("/**").hasRole("COMMON_USER")
//          //.antMatchers("/**").hasRole("ADMIN")
//          .and()
//          .formLogin().loginPage("/login")
//      ;
//    }

//  }
}