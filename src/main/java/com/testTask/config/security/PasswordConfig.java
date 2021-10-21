package com.testTask.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {

				if (rawPassword == null) {
					throw new IllegalArgumentException("rawPassword cannot be null");
				}
				if (encodedPassword == null || encodedPassword.length() == 0) {
					return false;
				}
				return encodedPassword.equals((String) rawPassword);
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return (String) rawPassword;
			}
		};
	}
}
