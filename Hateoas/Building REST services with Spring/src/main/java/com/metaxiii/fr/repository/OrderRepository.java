package com.metaxiii.fr.repository;

import com.metaxiii.fr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
