package com.mariannach.order;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
