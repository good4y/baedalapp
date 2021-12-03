package com.sparta.hw06.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class RestaurantDto {        //음식점 등록 Dto
    private Long id;
    private String name;
    private int deliveryFee;
    private int minOrderPrice;
}