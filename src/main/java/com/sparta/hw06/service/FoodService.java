package com.sparta.hw06.service;

import com.sparta.hw06.dto.FoodDto;
import com.sparta.hw06.model.Food;
import com.sparta.hw06.model.Restaurant;
import com.sparta.hw06.repository.FoodRepository;
import com.sparta.hw06.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerFood(@PathVariable Long restaurantId, List<FoodDto> foodDto){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        for (FoodDto food : foodDto)  {

            String name = food.getName();
            Food sameName = foodRepository.findByRestaurantAndName(restaurant, name);

            if (sameName != null) {
                throw new IllegalArgumentException("중복된 음식명");
            }

            int price = food.getPrice();
            if (price < 100 || price > 1000000 || price % 100 != 0) {
                throw new IllegalArgumentException("가격 기준 미달");
            }

            Food foods = Food.builder()
                    .restaurant(restaurant)
                    .name(name)
                    .price(price)
                    .build();

            foodRepository.save(foods);
        }
    }

    // 음식 조회
    public List<Food> readFoods(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        return foodRepository.findAllByRestaurant(restaurant);
    }
}
