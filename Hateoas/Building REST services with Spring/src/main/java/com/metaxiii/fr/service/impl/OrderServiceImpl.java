package com.metaxiii.fr.service.impl;

import com.metaxiii.fr.entity.Order;
import com.metaxiii.fr.exception.OrderException;
import com.metaxiii.fr.repository.OrderRepository;
import com.metaxiii.fr.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.metaxiii.fr.exception.OrderErrorCode.ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

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

    @Override
    public Order update(final UUID id, final Order ordery) {
        return repository.findById(id).map(order -> {
            order.setStatus(ordery.getStatus());
            return repository.save(order);
        }).orElseThrow(() -> new OrderException(ORDER_NOT_FOUND, id));
    }

    @Override
    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }
}
