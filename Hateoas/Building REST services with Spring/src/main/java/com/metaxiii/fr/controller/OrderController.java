package com.metaxiii.fr.controller;

import com.metaxiii.fr.assembler.OrderAssembler;
import com.metaxiii.fr.creator.OrderCreator;
import com.metaxiii.fr.dto.OrderDTO;
import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.exception.DatabindingException;
import com.metaxiii.fr.exception.OrderException;
import com.metaxiii.fr.input.OrderInput;
import com.metaxiii.fr.model.OrderModel;
import com.metaxiii.fr.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.metaxiii.fr.exception.OrderErrorCode.ORDER_NOT_FOUND;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderAssembler assembler;
    private final OrderService service;
    private final OrderCreator creator;
    private final Validator validator;

    @GetMapping("/orders")
    public ResponseEntity<CollectionModel<OrderModel>> orders() {
        final List<Order> orderEntities = service.findAll();
        return new ResponseEntity<>(assembler.toCollectionModel(orderEntities), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderModel> order(@PathVariable UUID id) {
        Order order = service.findById(id)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND, id));
        return new ResponseEntity<>(assembler.toModel(order), HttpStatus.OK);
    }

    @PostMapping(value = "orders", consumes = "application/json")
    public ResponseEntity<OrderModel> order(@RequestBody @Valid OrderInput input) {
        final Order saved = service.save(creator.toDomain(input));
        return new ResponseEntity<>(assembler.toModel(saved), HttpStatus.CREATED);
    }

    @PatchMapping(value = "orders/{id}", consumes = "application/json")
    public ResponseEntity<OrderModel> order(@PathVariable UUID id,
                                            @RequestBody OrderDTO orderDTO) {
        final Optional<Order> originalOrder = service.findById(id);
        final Order saved = originalOrder.map(order -> {
            this.validateDTO(orderDTO);
            order.setStatus(orderDTO.getStatus());
            return service.save(order);
        }).orElseThrow(() -> {
            throw new OrderException(ORDER_NOT_FOUND, id);
        });
        return new ResponseEntity<>(assembler.toModel(saved), HttpStatus.ACCEPTED);
    }


    @DeleteMapping(value = "orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validateDTO(final OrderDTO orderDTO) {
        DataBinder dataBinder = new DataBinder(orderDTO);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors())
            throw new DatabindingException(dataBinder.getBindingResult());
    }
}
