package com.metaxiii.fr.controller;

import com.metaxiii.fr.assembler.EmployeeModelAssembler;
import com.metaxiii.fr.exception.EmployeeException;
import com.metaxiii.fr.model.Employee;
import com.metaxiii.fr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
    public CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<EntityModel<Employee>> employees = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
    }

    @PostMapping("employees")
    public ResponseEntity<EntityModel<Employee>> newEmployee(@RequestBody Employee employee) {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(employee));
        assembler.toModel(repository.save(employee));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("employees/{id}")
    public EntityModel<Employee> getEmployee(@PathVariable long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .orElseThrow(
                        () -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<EntityModel<Employee>> replaceEmployee(@RequestBody Employee put, @PathVariable long id) {
        EntityModel<Employee> employeeEntityModel = assembler.toModel(repository.findById(id)
                .map(employee -> {
                    employee.setLastName(put.getLastName());
                    employee.setFirstName(put.getFirstName());
                    employee.setRole(put.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    put.setId(id);
                    return repository.save(put);
                }));
        return ResponseEntity
                .created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

    @DeleteMapping("employees/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
