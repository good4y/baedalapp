package com.sparta.hw06.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {      //음식 등록 Dto(request)
    private Long id;
    private Long restId;
    private String name;
    private int price;

    @Builder
    public FoodDto(Long restId, String name, int price){
        this.restId = restId;
        this.name = name;
        this.price = price;
    }

}
