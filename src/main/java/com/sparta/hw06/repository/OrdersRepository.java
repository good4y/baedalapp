package com.sparta.hw06.repository;

import com.sparta.hw06.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
