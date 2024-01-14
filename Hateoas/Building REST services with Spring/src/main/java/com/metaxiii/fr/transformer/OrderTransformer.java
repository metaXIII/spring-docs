package com.metaxiii.fr.transformer;

import com.metaxiii.fr.creator.DTOCreator;
import com.metaxiii.fr.creator.DomainCreator;
import com.metaxiii.fr.dto.OrderDTO;
import com.metaxiii.fr.input.OrderInput;
import org.springframework.stereotype.Component;

@Component
public class OrderTransformer implements DomainCreator<OrderInput, OrderDTO>, DTOCreator<OrderInput, OrderDTO> {

  @Override
  public OrderDTO toDTO(final OrderInput properties) {
    return OrderDTO.builder().status(properties.getStatus()).build();
  }

  @Override
  public OrderInput toDomain(final OrderDTO order) {
    return OrderInput.builder().status(order.getStatus()).build();
  }
}
