package com.metaxiii.fr.input;

import com.metaxiii.fr.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeInput {

  private String firstName;
  private String lastName;
  private Role role;
}
