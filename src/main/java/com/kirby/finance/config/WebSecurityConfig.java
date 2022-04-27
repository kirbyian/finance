package com.kirby.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.authorizeRequests((authorize) -> authorize
			.antMatchers("/css/**", "/index").permitAll()
			.antMatchers("/users/**").hasRole("USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
		).logout(logout -> {
			try {
				logout
				        .permitAll()
				        .logoutSuccessUrl("/index").and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login-error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER")
				.and().withUser("johndoe").password(passwordEncoder().encode("password")).roles("USER").and()
				.withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
