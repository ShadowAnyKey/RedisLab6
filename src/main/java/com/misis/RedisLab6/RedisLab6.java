package com.misis.RedisLab6;

import com.misis.RedisLab6.config.IntegrationConfig.StudentGateway;
import com.misis.RedisLab6.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class RedisLab6 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisLab6.class, args);
        StudentGateway gateway = context.getBean(StudentGateway.class);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int year = random.nextInt(5) + 2000;
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(28) + 1;

            // Создание объекта с случайными значениями
            Student student = new Student("Студент " + (i + 1), LocalDate.of(year, month, day), 1 + random.nextInt(100));
            gateway.send(student);
        }
    }
}
