package ba.tim2.RezervacijaKarata;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
//@EnableSwagger2
//@Configuration
public class RezervacijaKarataApplication implements CommandLineRunner {

//	@Autowired
//	private RezervacijaKarataService rezervacijaKarataService;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		RestTemplate noviRest = new RestTemplate();
		//noviRest.setErrorHandler(new RestTemplateResponseErrorHandler());
		return noviRest;
	}

	@Override
	public void run(String... args) throws Exception {
//		rezervacijaKarataService.insertStaticData();
	}

	@RequestMapping(value = "/")
	public String home() {
		return "Eureka Client application";
	}
	public static void main(String[] args) {
		SpringApplication.run(RezervacijaKarataApplication.class, args);
	}

}

@RefreshScope
@RestController
class MessageRestController {

	@Value("${message:Hello default}")
	private String message;

	@RequestMapping("/message")
	String getMessage() {
		return this.message;
	}
}
