package bwa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class BodyWeightApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BodyWeightApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
