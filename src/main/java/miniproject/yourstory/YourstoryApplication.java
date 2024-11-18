package miniproject.yourstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class YourstoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourstoryApplication.class, args);
    }

}
