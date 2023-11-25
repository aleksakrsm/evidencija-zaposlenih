package rs.ac.bg.fon.njt.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/webapp");
        
        SpringApplication.run(WebappApplication.class, args);
        
    }

}
