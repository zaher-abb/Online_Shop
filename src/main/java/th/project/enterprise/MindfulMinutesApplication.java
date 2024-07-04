package th.project.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MindfulMinutesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindfulMinutesApplication.class, args);
    }

}
