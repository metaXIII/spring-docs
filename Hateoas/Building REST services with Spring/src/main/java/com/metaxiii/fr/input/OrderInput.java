package com.metaxiii.fr.input;

import com.metaxiii.fr.enums.Status;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderInput {

  private String description;
  private Status status;
}
