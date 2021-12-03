package com.sparta.hw06.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;             // 식당 Primary Key

    @Column(nullable = false)
    private String name;         // 식당 이름

    @Column(nullable = false)
    private int deliveryFee;  // 배달비 (0~10,000, 500단위)

    @Column(nullable = false)
    private int minOrderPrice;       // 최소 주문 가격(1000~100,000, 1,000단위)

    @Builder
    public Restaurant(String name, int deliveryFee, int minOrderPrice){
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.minOrderPrice = minOrderPrice;
    }
}
