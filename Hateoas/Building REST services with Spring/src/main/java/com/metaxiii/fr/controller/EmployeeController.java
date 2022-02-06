package com.metaxiii.fr.controller;

import com.metaxiii.fr.assembler.EmployeeModelAssembler;
import com.metaxiii.fr.exception.EmployeeException;
import com.metaxiii.fr.model.Employee;
import com.metaxiii.fr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.metaxiii.fr.exception.EmployeeErrorCode.EMPLOYEE_NOT_FOUND;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    @GetMapping("employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll()
                .stream()
                .map(employee -> EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"))).toList();
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("employees")
    public Employee newEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("employees/{id}")
    public EntityModel<Employee> getEmployee(@PathVariable long id) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
        return assembler.toModel(employee);
    }

    @PutMapping("employees/{id}")
    Employee replaceEmployee(@RequestBody Employee put, @PathVariable long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(put.getName());
                    employee.setRole(put.getRole());
                    return repository.save(employee);
                }).orElseGet(() -> {
                    put.setId(id);
                    return repository.save(put);
                });
    }

    @DeleteMapping("employees/{id}")
    void deleteEmployee(@PathVariable long id) {
        repository.deleteById(id);
    }
}
