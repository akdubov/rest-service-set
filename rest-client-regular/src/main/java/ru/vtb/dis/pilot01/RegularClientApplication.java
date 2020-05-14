package ru.vtb.dis.pilot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RegularClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(RegularClientApplication.class, args);
    }

}
