package tn.esprit.spring.khaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "tn.esprit.spring.khaddem.services")
@ComponentScan(basePackages = "tn.esprit.spring.khaddem")
public class KhaddemApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhaddemApplication.class, args);
    }

}
