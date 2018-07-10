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

import cn.tz.www.customer.controller.service.SecurityCustomerDetailsService;

/**
 *
 */

@EnableWebSecurity
public class SecurityConfig {

	/**
	 * 
	 * 接口Url请求过滤定义
	 * 
	 */
	@Configuration
	@Order(1)
	public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private javax.sql.DataSource dataSource;
		@Autowired
		SecurityCustomerDetailsService securityCustomerDetailsService;
		@Autowired
		private PasswordEncoder passwordEncoder;

		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().antMatcher("/api/**").authorizeRequests().antMatchers("/api/customer/login")
					.permitAll().antMatchers("/api/customer/reset").permitAll().antMatchers("/api/customer/loadById")
					.permitAll()

					// 公共资源不需要认证
					.antMatchers("/api/customer/public/*").permitAll().antMatchers("/api/customer/*").authenticated()
					.antMatchers("/api/customer/vip/*").hasAnyRole("VIP")

			;
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(securityCustomerDetailsService).passwordEncoder(passwordEncoder);
			// 给内存加载一个用户
			auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");

		}

		@Bean
		public JdbcTokenRepositoryImpl jdbcTokenRepository() {
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
			web.ignoring().antMatchers("/resources/**",
					"/webjars/**", 
					"/js/**", 
					"/images/**",
					"/css/**",
					"/h2-console/*",
					"/assets/*", 
					"/web/**");
		}

		protected void configure(HttpSecurity http) throws Exception {

			// 关闭X-Frame-Options
			http.headers().frameOptions().disable();
			http.authorizeRequests()
			.antMatchers("/", "/web/index")
		    .permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.permitAll().and()
			.logout().permitAll();
		}

	}
}