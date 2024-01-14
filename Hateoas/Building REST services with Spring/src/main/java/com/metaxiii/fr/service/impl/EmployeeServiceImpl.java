package com.metaxiii.fr.service.impl;

import static com.metaxiii.fr.exception.EmployeeErrorCode.EMPLOYEE_NOT_FOUND;

import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.exception.EmployeeException;
import com.metaxiii.fr.repository.EmployeeRepository;
import com.metaxiii.fr.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  @Override
  public List<Employee> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Employee> findById(final UUID id) {
    return repository.findById(id);
  }

  @Override
  public Employee save(final Employee entity) {
    return repository.save(entity);
  }

  @Override
  public Employee update(final UUID id, final Employee employeeEntity) {
    return repository
      .findById(id)
      .map(employee -> {
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        return repository.save(employee);
      })
      .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
  }

  @Override
  public void delete(final UUID id) {
    repository.deleteById(id);
  }
}
