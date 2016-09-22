package fun.zepo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>Spring Security Configurations</strong>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * @return Md5PasswordEncoder
	 */
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/resources/**", "/checkExists/**", "/signup/**", "/api/**").permitAll()
				.antMatchers("/profile/**").authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login?error").usernameParameter("email").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/login?logout").and().csrf().disable();

		/*
		 * http .csrf().disable() .exceptionHandling()
		 * .authenticationEntryPoint(restAuthenticationEntryPoint) .and()
		 * .authorizeRequests() .antMatchers("/api/foos").authenticated() .and()
		 * .formLogin() .successHandler(authenticationSuccessHandler)
		 * .failureHandler(new SimpleUrlAuthenticationFailureHandler()) .and()
		 * .logout();
		 */
	}

	/**
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}