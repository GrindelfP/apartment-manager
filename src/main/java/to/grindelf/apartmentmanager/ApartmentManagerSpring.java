package to.grindelf.apartmentmanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "to.grindelf.apartmentmanager")
@Configuration(proxyBeanMethods = false)
public class ApartmentManagerSpring {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentManagerSpring.class, args);
    }
}