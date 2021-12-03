package com.sparta.hw06.controller;

import com.sparta.hw06.dto.RestaurantDto;
import com.sparta.hw06.model.Restaurant;
import com.sparta.hw06.repository.RestaurantRepository;
import com.sparta.hw06.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor        //순환 방지(생성자주입)
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")    //식당 등록
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.registerRestaurant(restaurantDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){
        return restaurantService.readRestaurant();
    }
}
