package com.metaxiii.fr.service;

import com.metaxiii.fr.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(UUID id);

    Order save(Order order);

    Order update(UUID id, Order order);

    void deleteById(UUID id);
}
