package com.sparta.hw06.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {     //주문 조회
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    @Builder
    public OrderDto(String restaurantName, List<FoodOrderDto> foods, int deliveryFee, int totalPrice) {
            this.restaurantName = restaurantName;
            this.foods = foods;
            this.deliveryFee = deliveryFee;
            this.totalPrice = totalPrice;
    }
}