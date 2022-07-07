package com.metaxiii.fr.config;

import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.enums.Role;
import com.metaxiii.fr.enums.Status;
import com.metaxiii.fr.repository.EmployeeRepository;
import com.metaxiii.fr.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            employeeRepository.save(Employee.builder()
                    .firstName("Bilbo")
                    .lastName("Baggins")
                    .role(Role.BURGLAR)
                    .build());
            employeeRepository.save(Employee.builder()
                    .firstName("Frodo")
                    .lastName("Baggins")
                    .role(Role.THIEF).build());
            employeeRepository.findAll().forEach(employeeEntity -> log.info("Preloaded " + employeeEntity));
            orderRepository.save(Order.builder().description("MacBook Pro").status(Status.COMPLETED).build());
            orderRepository.save(Order.builder().description("iPhone").status(Status.IN_PROGRESS).build());
            orderRepository.findAll().forEach(orderEntity -> log.info("Preloaded " + orderEntity));
        };
    }
}
