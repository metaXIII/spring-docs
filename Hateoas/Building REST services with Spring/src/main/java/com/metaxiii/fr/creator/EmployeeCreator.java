package com.metaxiii.fr.creator;

import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.input.EmployeeInput;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeCreator implements DomainCreator<Employee, EmployeeInput> {

  @Override
  public Employee toDomain(final EmployeeInput input) {
    return Employee
      .builder()
      .id(UUID.randomUUID())
      .firstName(input.getFirstName())
      .lastName(input.getLastName())
      .role(input.getRole())
      .build();
  }
}
