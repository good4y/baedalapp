package com.sparta.hw06.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequestDto {          //주문 요청
    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;        //food id, quantity

    @Builder
    public OrderRequestDto(Long restaurantId, List<FoodOrderRequestDto> foods) {
        this.restaurantId = restaurantId;
        this.foods = foods;
    }
}