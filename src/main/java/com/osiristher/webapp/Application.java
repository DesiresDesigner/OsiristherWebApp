package com.osiristher.webapp;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.orm.jpa.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import javax.inject.Inject;

//@EntityScan(basePackages = "com.osiristher.webapp.dbtest.domain.*")
//@EnableJpaRepositories("com.osiristher.webapp.dbtest.service.*")
@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    //@Inject
    //StudentRepo studentRepo;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(StudentRepo repo) {
        return (args) -> {
            repo.save(new Student("Boris"));


            Iterable<Student> items = repo.findAll();

            for(Student item : items) {

                log.info(item.getId() + ": " + item.getName());
            }

        };
    }*/

}
