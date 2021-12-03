package com.sparta.hw06.controller;

import com.sparta.hw06.dto.FoodDto;
import com.sparta.hw06.dto.OrderDto;
import com.sparta.hw06.dto.OrderRequestDto;
import com.sparta.hw06.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/order/request")       // 유저 -> 식당
    public OrderDto orderRequest(@RequestBody OrderRequestDto orderRequestDto) {
        return ordersService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")              // 주문 조회
    public List<OrderDto> orderCheck() {
       return ordersService.checkOrder();
    }
}