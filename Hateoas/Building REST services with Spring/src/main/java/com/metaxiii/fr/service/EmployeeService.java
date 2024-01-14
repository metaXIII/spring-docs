package com.metaxiii.fr.service;

import com.metaxiii.fr.entity.Employee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
  void delete(final UUID id);

  List<Employee> findAll();

  Optional<Employee> findById(final UUID id);

  Employee save(final Employee entity);

  Employee update(final UUID id, final Employee employee);
}
