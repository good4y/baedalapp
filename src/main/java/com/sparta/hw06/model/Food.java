package com.sparta.hw06.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //Primary Key

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;        //fk

    @Column
    private String name;        //음식 종류     fk(orderdetails)

    @Column
    private int price;   //음식 값

    @Builder
    public Food(String name, Restaurant restaurant, int price){
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }
}
