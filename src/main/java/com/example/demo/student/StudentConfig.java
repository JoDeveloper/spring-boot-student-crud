package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student joseph = new Student(1L, "Joseph",  LocalDate.of(1995, 9, 12), "jodeveloper8@gmail.com");
            Student alex = new Student("Alex",  LocalDate.of(2000, 1, 20), "alex@gmail.com");
            repository.saveAll(List.of(joseph,alex));
        };
    }
}
