package io.github.choijy.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description : Security Configuration class.
 *
 * Created by jychoi on 2022/06/26.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Authorization
		httpSecurity.authorizeRequests()
			.mvcMatchers("/", "/info", "/account/**").permitAll()
			.mvcMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
		// Form Login
		httpSecurity.formLogin();
		// Http Default Setting
		httpSecurity.httpBasic();
	}

	// In-memory authorization
	// @Override
	// protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	// 	// 쉽게 아래의 값이 DB에 들어가는 값이라고 생각.
	// 	authenticationManagerBuilder.inMemoryAuthentication()
	// 		.withUser("admin").password("{noop}123").roles("USER").and()
	// 		.withUser("user").password("{noop}456").roles("USER");
	// }

	// Spring 5 이전에선 NoOp이 default.
	// 이후에는 BES가 default.
	// {noop} password 이런 형태를 가지게 된 게 하위 호환성을 위해서임.
	// 참고는 공식 문서.
	@Bean
	public PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
