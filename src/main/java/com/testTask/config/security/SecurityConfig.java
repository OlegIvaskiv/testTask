package com.testTask.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.testTask.service.impl.EmployeeServiceImpl;

@Configuration
@ComponentScan("com.testTask")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private EmployeeServiceImpl employeeService;

	private PasswordEncoder passwordEncoder;

	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	public SecurityConfig(EmployeeServiceImpl employeeService, PasswordEncoder passwordEncoder, LoginSuccessHandler loginSuccessHandler) {
		this.employeeService = employeeService;
		this.passwordEncoder = passwordEncoder;
		this.loginSuccessHandler = loginSuccessHandler;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new EmployeeServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(employeeService);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/com/testTask/employees/*").hasAuthority("Employee")
				.anyRequest().authenticated().and().formLogin().successHandler(loginSuccessHandler).permitAll().and()
				.logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
}
