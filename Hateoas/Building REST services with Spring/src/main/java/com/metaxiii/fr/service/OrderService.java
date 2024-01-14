package com.metaxiii.fr.service;

import com.metaxiii.fr.entity.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
  void deleteById(UUID id);

  List<Order> findAll();

  Optional<Order> findById(UUID id);

  Order save(Order order);
}
