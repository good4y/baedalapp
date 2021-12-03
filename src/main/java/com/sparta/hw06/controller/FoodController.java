package com.sparta.hw06.controller;

import com.sparta.hw06.dto.FoodDto;
import com.sparta.hw06.model.Food;
import com.sparta.hw06.model.Restaurant;
import com.sparta.hw06.repository.FoodRepository;
import com.sparta.hw06.repository.RestaurantRepository;
import com.sparta.hw06.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")    //음식 등록
    public void registerRestaurant(@PathVariable Long restaurantId, @RequestBody List<FoodDto> food) {
        foodService.registerFood(restaurantId, food);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){

        return foodService.readFoods(restaurantId);
    }
}
