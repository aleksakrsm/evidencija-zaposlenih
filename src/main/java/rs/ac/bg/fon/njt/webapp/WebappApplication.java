package rs.ac.bg.fon.njt.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/webapp");
        
        SpringApplication.run(WebappApplication.class, args);
   
    }

}
