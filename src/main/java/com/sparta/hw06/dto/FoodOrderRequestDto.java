package com.sparta.hw06.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodOrderRequestDto {  //orderrequest 안에 들어감
    Long id;        //food Id
    int quantity;

    @Builder
    public FoodOrderRequestDto(Long foodId, int quantity){
        this.id = foodId;
        this.quantity = quantity;

    }
}
