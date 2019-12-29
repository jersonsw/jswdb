package io.inouty.jswdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@ComponentScan({
        "io.inouty"
})
@EnableJpaRepositories(basePackages = {
        "io.inouty.jswdb.data.repositories"
})
@EntityScan(basePackages = {
        "io.inouty.jswdb.data.entities"
})
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :" + new Date());   // It will print UTC timezone
    }


}
