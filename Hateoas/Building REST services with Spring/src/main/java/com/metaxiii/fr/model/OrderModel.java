package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.metaxiii.fr.input.OrderInput;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel extends RepresentationModel<OrderModel> {

  @With
  private UUID id;

  @With
  @JsonUnwrapped
  private OrderInput properties;

  @Override
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
