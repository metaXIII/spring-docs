package com.metaxiii.fr.assembler;

import com.metaxiii.fr.controller.EmployeeController;
import com.metaxiii.fr.entity.Employee;
import com.metaxiii.fr.model.EmployeeModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeAssembler implements RepresentationModelAssembler<Employee, EmployeeModel> {
    @Override
    public EmployeeModel toModel(Employee entity) {
        return EmployeeModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .role(entity.getRole().getPosition())
                .build()
                .add(linkTo(methodOn(EmployeeController.class).getEmployee(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withRel("List of employees"));
    }
}
