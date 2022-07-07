package com.metaxiii.fr.controller;

import com.metaxiii.fr.assembler.EmployeeAssembler;
import com.metaxiii.fr.creator.EmployeeCreator;
import com.metaxiii.fr.dto.EmployeeDTO;
import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.exception.DatabindingException;
import com.metaxiii.fr.exception.EmployeeException;
import com.metaxiii.fr.input.EmployeeInput;
import com.metaxiii.fr.model.EmployeeModel;
import com.metaxiii.fr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.metaxiii.fr.exception.EmployeeErrorCode.EMPLOYEE_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeAssembler assembler;
    private final EmployeeCreator creator;
    private final Validator validator;
    private final EmployeeService service;

    @GetMapping("employees")
    public ResponseEntity
            <CollectionModel<EmployeeModel>> getAllEmployees() {
        final List<Employee> employees = service.findAll();
        return new ResponseEntity<>(assembler.toCollectionModel(employees), HttpStatus.OK);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable UUID id) {
        final Employee employeeEntity = service.findById(id)
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
        final EmployeeModel employeeModel = assembler.toModel(employeeEntity);
        return new ResponseEntity<>(employeeModel, HttpStatus.OK);
    }


    @PostMapping(value = "employees", consumes = "application/json")
    public ResponseEntity<EmployeeModel> newEmployee(@RequestBody @Valid EmployeeInput input) {
        final Employee employee = service.save(creator.toDomain(input));
        return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.CREATED);
    }

    @PatchMapping(value = "employees/{id}", consumes = "application/json")
    public ResponseEntity<EmployeeModel> replaceEmployee(@PathVariable UUID id,
                                                         @RequestBody @Valid EmployeeDTO employeeDTO) {
        final Optional<Employee> originalEmployee = service.findById(id);
        final Employee saved = originalEmployee.map(employee -> {
            this.validateDTO(employeeDTO);
            employee.setFirstName(employee.getFirstName());
            employee.setLastName(employee.getLastName());
            return service.save(employee);
        }).orElseThrow(() -> {
            throw new EmployeeException(EMPLOYEE_NOT_FOUND, id);
        });
        return new ResponseEntity<>(assembler.toModel(saved), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("employees/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validateDTO(final EmployeeDTO employeeDTO) {
        DataBinder dataBinder = new DataBinder(employeeDTO);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors())
            throw new DatabindingException(dataBinder.getBindingResult());
    }
}
