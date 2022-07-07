package com.metaxiii.fr.assembler;

import com.metaxiii.fr.controller.OrderController;
import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.input.OrderInput;
import com.metaxiii.fr.model.OrderModel;
import com.metaxiii.fr.transformer.OrderTransformer;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class OrderAssembler implements RepresentationModelAssembler<Order, OrderModel> {
    private final OrderTransformer transformer;

    @Override
    public OrderModel toModel(Order order) {
        return OrderModel.builder()
                .id(order.getId())
                .properties(OrderInput.builder()
                        .description(order.getDescription())
                        .status(order.getStatus())
                        .build()).build()
                .add(linkTo(methodOn(OrderController.class).order(order.getId())).withSelfRel())
                .add(linkTo(methodOn(OrderController.class).orders()).withRel("List of orders"));
    }
}
