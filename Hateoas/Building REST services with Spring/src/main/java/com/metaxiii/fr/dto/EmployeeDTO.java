package com.metaxiii.fr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class EmployeeDTO {
    @NotNull
    @Size(min = 3, max = 25)
    private final String firstName;

    @NotNull
    @Size(min = 3, max = 25)
    private final String lastName;
}
