package com.min.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.min.common.CustomLoginSuccessHandler;
import com.min.valid.service.MemberServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberServiceImpl userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/", "/h2-console/**", "/member/**").permitAll()
				.antMatchers("/api/v1/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login") // 로그인 뷰 페이지 연결
					.loginProcessingUrl("/login") // POST로 로그인 처리할 url
					.defaultSuccessUrl("/") // 로그인 성공 후 이동할 페이지
					.failureUrl("/login") // 로그인 실패 후 이동할 페이지 (default: /login?error)
				.and()
					.logout();

//		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.csrf().disable();
		
		http.headers().frameOptions().disable();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	  return new CustomLoginSuccessHandler("/");//default로 이동할 url
	}
	
}