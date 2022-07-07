package com.metaxiii.fr.transformer;

import com.metaxiii.fr.creator.DomainCreator;
import com.metaxiii.fr.dto.EmployeeDTO;
import com.metaxiii.fr.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformer implements DomainCreator<Employee, EmployeeDTO> {
    @Override
    public Employee toDomain(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .build();
    }
}
