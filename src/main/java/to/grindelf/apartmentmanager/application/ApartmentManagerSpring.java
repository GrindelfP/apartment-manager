package to.grindelf.apartmentmanager.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration(proxyBeanMethods = false)
public class ApartmentManagerSpring {

    public ApartmentManagerSpring() {}

    // TODO: implement the constructor
    // TODO: find out how to implement the spring app basis and how to run it

    public static void startSpringBootApplication(String[] args) {
        SpringApplication.run(ApartmentManagerSpring.class, args);
    }

}