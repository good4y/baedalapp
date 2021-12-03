package com.sparta.hw06.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {     //response
    String name;
    int quantity;
    int price;

    @Builder
    public FoodOrderDto(String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

