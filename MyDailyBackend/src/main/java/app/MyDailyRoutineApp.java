package app;

import common.GetLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.UserRepository;
import security.PassowrdEncoder;
import security.config.WebSecurityConfig;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan("entity")
@ComponentScan(basePackages = {"controller", "service", })
@Configuration
@Import({WebSecurityConfig.class, PassowrdEncoder.class})
public class MyDailyRoutineApp implements GetLogger {

    public static void main(String[] args) {
        SpringApplication.run(MyDailyRoutineApp.class, args);
    }

}