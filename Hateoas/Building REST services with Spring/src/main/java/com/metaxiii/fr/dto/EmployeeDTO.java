package com.metaxiii.fr.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
