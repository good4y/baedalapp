package com.sparta.hw06.repository;

import com.sparta.hw06.model.Food;
import com.sparta.hw06.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
    Food findByRestaurantAndName(Restaurant restaurant, String name);
    Food findByRestaurantAndId(Restaurant restaurant, Long id);

}
