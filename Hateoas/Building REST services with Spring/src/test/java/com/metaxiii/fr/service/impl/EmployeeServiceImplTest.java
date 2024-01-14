package com.metaxiii.fr.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.exception.EmployeeException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ComponentScan("com.metaxiii.fr.service")
@Sql(scripts = "classpath:/init-db/employee.sql")
class EmployeeServiceImplTest {

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Test
  void itShouldFindAll() {
    assertDoesNotThrow(() -> {
      final List<Employee> all = employeeService.findAll();
      assertEquals(1, all.size());
    });
  }

  @Test
  void itShouldFindById() {
    assertDoesNotThrow(() -> employeeService.findById(UUID.fromString("e0b3a533-839c-453a-9874-4efe053e53df")));
  }

  @Test
  void shouldDelete() {
    assertDoesNotThrow(() -> employeeService.delete(UUID.fromString("e0b3a533-839c-453a-9874-4efe053e53df")));
  }

  @Test
  void shouldSave() {
    assertDoesNotThrow(() -> {
      employeeService.save(Employee.builder().id(UUID.randomUUID()).build());
    });
  }

  @Test
  void shouldUpdateEmployee() {
    assertDoesNotThrow(() -> {
      employeeService.update(UUID.fromString("e0b3a533-839c-453a-9874-4efe053e53df"), Employee.builder().build());
    });
  }

  @Test
  void shouldUpdateIfNotFound() {
    final UUID uuid = UUID.randomUUID();
    final Employee employee = Employee.builder().build();
    assertThrows(EmployeeException.class, () -> employeeService.update(uuid, employee));
  }
}
