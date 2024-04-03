package com.misis.RedisLab6.config;

import com.misis.RedisLab6.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.integration.annotation.ServiceActivator;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel filteredChannel() {
        return new DirectChannel();
    }

    @Bean
    @Filter(inputChannel = "outputChannel", outputChannel = "filteredChannel")
    public MessageSelector oddNumberSelector() {
        return message -> ((Student) message.getPayload()).getRandomValue() % 2 != 0;
    }

    @Bean
    @ServiceActivator(inputChannel = "filteredChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            Student student = (Student) message.getPayload();
            System.out.println("Получено сообщение с нечетным randomValue: " + student.getFullName() + ", Дата рождения: " + student.getBirthday() + ", Случайное значение: " + student.getRandomValue());
        };
    }

    @MessagingGateway(defaultRequestChannel = "outputChannel")
    public interface StudentGateway {
        void send(Student student);
    }
}