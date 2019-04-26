package com.min.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.min.valid.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${spring.security.enable-csrf}")
    private boolean csrfEnabled;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/", "/h2-console/**", "/member/**").permitAll()
				.antMatchers("/api/v1/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
//					.usernameParameter("uid")
//					.passwordParameter("passwd")
//					.loginProcessingUrl("/customLogin")
//					.defaultSuccessUrl("/").permitAll()
				.and()
				.logout()
//					.logoutUrl("/customLogout")
//					.logoutSuccessUrl("/")
//					.invalidateHttpSession(true).permitAll()
				.and()
				.csrf().disable();

		if(!csrfEnabled)
	       {
	         http.csrf().disable();
	       }
		
		http.headers().frameOptions().disable();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}