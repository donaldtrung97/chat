package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@ComponentScan("hello")

public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("soaica").password("123456").roles("USER").and().withUser("mi")
				.password("654321").roles("USER").and().withUser("soaica1").password("123456").roles("USER");
		
		auth.inMemoryAuthentication().withUser("soaica2").password("123456").roles("USER").and().withUser("mi2")
		.password("654321").roles("USER");
		auth.inMemoryAuthentication().withUser("bi").password("1234").roles("USER");
		auth.inMemoryAuthentication().withUser("bibi").password("1234").roles("USER");
		auth.inMemoryAuthentication().withUser("trung").password("1234").roles("USER");
		auth.inMemoryAuthentication().withUser("he").password("1234").roles("USER");
		
		

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/").hasRole("USER").anyRequest().authenticated().and().httpBasic();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
