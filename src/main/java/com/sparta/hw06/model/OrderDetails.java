package com.sparta.hw06.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class OrderDetails {     //quantity, restaurantId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //주문조회 음식이랑 연결
    private Long id;

    @Column
    private String name;            //음식이름 fk

    @Column
    private int quantity;

    @Column
    private int price;

    @Builder
    public OrderDetails(String name, int quantity, int price){
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }
}
