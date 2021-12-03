package com.sparta.hw06.service;

import com.sparta.hw06.dto.RestaurantDto;
import com.sparta.hw06.model.Restaurant;
import com.sparta.hw06.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    //식당 등록
    public Restaurant registerRestaurant(RestaurantDto requestDto)  {
        String name = requestDto.getName();

        Restaurant sameName = restaurantRepository.findByName(name).orElse(null);

        if (sameName != null) {
            throw new IllegalArgumentException("중복된 가게명");
        }

        int deliveryFee = requestDto.getDeliveryFee();
        if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("허용되지 않은 배달비 (0 ~ 10,000)");
        }

        if (deliveryFee % 500 != 0){
            throw new IllegalArgumentException("허용되지 않은 배달비 (0 ~ 10,000)");
        }

        int minOrderPrice = requestDto.getMinOrderPrice();
        if(minOrderPrice < 1000 || minOrderPrice > 100000 || minOrderPrice % 100 != 0){
            throw  new IllegalArgumentException("허용되지 않은 최소 주문 가격 (0 ~ 100,000)" );
        }

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .deliveryFee(deliveryFee) // 배달비 (0~10,000, 500단위)
                .minOrderPrice(minOrderPrice)
                .build();
        return restaurantRepository.save(restaurant);
    }

    @Transactional      //식당 조회
    public List<Restaurant> readRestaurant (){
        return restaurantRepository.findAll();
    }
}