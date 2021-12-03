package com.sparta.hw06.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Orders {   //유저랑 연결

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int deliverFee;

    @Column
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Builder
    public Orders(Restaurant restaurant, int deliveryFee, int totalPrice) {
        this.restaurant = restaurant;
        this.deliverFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
