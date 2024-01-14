package com.metaxiii.fr.config;

import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.enums.Role;
import com.metaxiii.fr.enums.Status;
import com.metaxiii.fr.repository.EmployeeRepository;
import com.metaxiii.fr.repository.OrderRepository;
import java.util.UUID;
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
      employeeRepository.save(
        Employee
          .builder()
          .id(UUID.fromString("329fc021-4cdd-4f72-882c-423f5d86bd80"))
          .firstName("Bilbo")
          .lastName("Baggins")
          .role(Role.BURGLAR)
          .build()
      );
      employeeRepository.save(
        Employee
          .builder()
          .id(UUID.fromString("6edce3e1-8d03-41ea-ada6-1ab8226d860a"))
          .firstName("Frodo")
          .lastName("Baggins")
          .role(Role.THIEF)
          .build()
      );
      employeeRepository.findAll().forEach(employeeEntity -> log.info("Preloaded " + employeeEntity));
      orderRepository.save(
        Order
          .builder()
          .id(UUID.fromString("8a6bc99f-d4cb-43ba-a490-fc54efed12b8"))
          .description("MacBook Pro")
          .status(Status.COMPLETED)
          .build()
      );
      orderRepository.save(
        Order
          .builder()
          .id(UUID.fromString("0c41ba0c-10ca-4ccd-bc8c-2781d4e19501"))
          .description("iPhone")
          .status(Status.IN_PROGRESS)
          .build()
      );
      orderRepository.findAll().forEach(orderEntity -> log.info("Preloaded " + orderEntity));
    };
  }
}
