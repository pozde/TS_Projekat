package ba.tim2.upravljanjefilmovimakartama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UpravljanjeFilmovimaKartamaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpravljanjeFilmovimaKartamaApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        RestTemplate noviRest = new RestTemplate();
        //noviRest.setErrorHandler(new RestTemplateResponseErrorHandler());
        return noviRest;
    }

}
