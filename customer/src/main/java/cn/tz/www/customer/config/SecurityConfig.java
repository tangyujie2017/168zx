package cn.tz.www.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.*;


/**
 *
 */

@EnableWebSecurity
public class SecurityConfig {
  
  

 
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
     
      http
          .csrf().disable()
          .antMatcher("/api/**")
          .authorizeRequests()
          .antMatchers("/api/customer/login").permitAll()
          .antMatchers("/api/customer/reset").permitAll()
          .antMatchers("/api/customer/loadById").permitAll()
         
          //公共资源不需要认证
          .antMatchers("/api/customer/public/*").permitAll()
          .antMatchers("/api/customer/*").authenticated()
          .antMatchers("/api/customer/vip/*").hasAnyRole("VIP")
         
      ;
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
      JdbcTokenRepositoryImpl impl = new JdbcTokenRepositoryImpl();
      impl.setDataSource(dataSource);
      return impl;
    }

   

  }

  @Configuration
  @Order(2)
  public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers(
          "/resources/**",
          "/webjars/**",
          "/js/**",
          "/images/**",
          "/css/**",
          "/h2-console/*",
          "/assets/*",
          "/web/**"
      );
    }
    
    protected void configure(HttpSecurity http) throws Exception {
    	  http.formLogin()          // 定义当需要用户登录时候，转到的登录页面。
    	      .loginPage("/web/login")      // 设置登录页面
    	      .loginProcessingUrl("/web/index") // 自定义的登录接口
    	      .and()
    	      .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
    	      .antMatchers("/web/login").permitAll()   // 设置所有人都可以访问登录页面
    	      .anyRequest()        // 任何请求,登录后可以访问
    	      .authenticated()
    	      .and()
    	      .csrf().disable();     // 关闭csrf防护
    	}
   

  }
}