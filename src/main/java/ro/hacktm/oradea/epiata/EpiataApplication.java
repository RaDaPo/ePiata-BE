package ro.hacktm.oradea.epiata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.hacktm.oradea.epiata.service.ExternalServices;

@SpringBootApplication
public class EpiataApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpiataApplication.class, args);
    }

}
