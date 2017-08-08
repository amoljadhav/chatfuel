package chatfuel.dcx.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan("chatfuel.dcx.springboot")
@SpringBootApplication(scanBasePackages={"chatfuel.dcx.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringBootStandAloneApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStandAloneApp.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(SpringBootStandAloneApp.class);
	    }
}
