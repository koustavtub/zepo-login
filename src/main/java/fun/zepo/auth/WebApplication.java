package fun.zepo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Koustav <br>
 *         Year: {2016
 *         <hr>
 *         <strong>Initiates The Web Application..Base of App</strong>
 */
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}
}
