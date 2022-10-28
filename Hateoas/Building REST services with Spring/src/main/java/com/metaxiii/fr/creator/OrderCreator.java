package com.metaxiii.fr.creator;

import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.input.OrderInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class OrderCreator implements DomainCreator<Order, OrderInput> {

    @Override
    public Order toDomain(final OrderInput input) {
        return Order.builder()
                .id(UUID.randomUUID())
                .description(input.getDescription())
                .status(input.getStatus())
                .build();
    }
}
