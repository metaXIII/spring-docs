package com.metaxiii.fr.service.impl;

import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.repository.OrderRepository;
import com.metaxiii.fr.service.OrderService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;

  @Override
  public void deleteById(final UUID id) {
    repository.deleteById(id);
  }

  @Override
  public List<Order> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Order> findById(final UUID id) {
    return repository.findById(id);
  }

  @Override
  public Order save(final Order order) {
    return repository.save(order);
  }
}
