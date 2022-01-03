package by.malinka.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Training Videos")
						.contact(new Contact().name("pasyagitka")
								.email("lizavetazinovich@gmail.com")))
				.servers(List.of(new Server().url("http://localhost:8081")
						.description("Dev service")));
	}*/


	private final JwtTokenProvider tokenProvider;

	@Autowired
	public SpringSecurityConfig(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/user/*").permitAll()
				/*.antMatchers("/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/book-service.html",
						"/swagger-ui.html",
						"/webjars/**").permitAll()*/
				.anyRequest().authenticated();
		http.apply(new JwtTokenConfigurer(tokenProvider));
	}

}