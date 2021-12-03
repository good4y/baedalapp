package com.sparta.hw06.service;

import com.sparta.hw06.dto.FoodOrderDto;
import com.sparta.hw06.dto.FoodOrderRequestDto;
import com.sparta.hw06.dto.OrderDto;
import com.sparta.hw06.dto.OrderRequestDto;
import com.sparta.hw06.model.Food;
import com.sparta.hw06.model.OrderDetails;
import com.sparta.hw06.model.Orders;
import com.sparta.hw06.model.Restaurant;
import com.sparta.hw06.repository.FoodRepository;
import com.sparta.hw06.repository.OrderDetailsRepository;
import com.sparta.hw06.repository.OrdersRepository;
import com.sparta.hw06.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrdersRepository ordersRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    // 주문 요청     ==> 식당명, foodorderdto, 배달비, 총 음식값 반환
    // OrderRequestDto를 받아서 내부의 정보로 responseDto 반환
    //
    @Transactional
    public OrderDto createOrder(OrderRequestDto orderRequestDto) {   //식당 Id, foodid, quantity 주입
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElse(null);  //식당 검색(id)
        List<FoodOrderDto> foodOrderDto = new ArrayList<>();         //name, quantity, price 반환값에 저장해야함
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);  //식당의 음식 조회
        List<OrderDetails> orderDetails = new ArrayList<>();      //food, quantity, price repository저장용
        int totalPrice = 0;
        for (FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()) {
            for (Food food : foods) {
                if (food.getId().equals(foodOrderRequestDto.getId())) {
                    if (foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100)
                        throw new IllegalArgumentException("수량확인");
                    int quantity = foodOrderRequestDto.getQuantity();
                    int price = food.getPrice() * quantity;

                    orderDetails.add(new OrderDetails(food.getName(),quantity, price));

                    totalPrice += price;

                    foodOrderDto.add(new FoodOrderDto(food.getName(), quantity, price));    //List에 객체 생성, 값 담기
                }
            }
        }
        int deliverFee = restaurant.getDeliveryFee();
        if (totalPrice < restaurant.getMinOrderPrice())
            throw new IllegalArgumentException("최소 주문 금액 확인");

        Orders orders = Orders.builder()
                .restaurant(restaurant)
                .deliveryFee(deliverFee)
                .totalPrice(totalPrice)
                .build();

        ordersRepository.save(orders);
        orderDetailsRepository.saveAll(orderDetails);

        OrderDto orderDto = OrderDto.builder()
                .restaurantName(restaurant.getName())
                .foods(foodOrderDto)                                //foodorderdto 넣기
                .totalPrice(totalPrice + deliverFee)
                .deliveryFee(restaurant.getDeliveryFee())
                .build();

        return orderDto;
    }

    //주문 조회
    public List<OrderDto> checkOrder() { // response Body
        List<OrderDto> orderDtos = new ArrayList<>();        //레스토랑, 배달비,
        List<FoodOrderDto> foodOrderDtos = new ArrayList<>(); //food id, quantity
        List<Orders> orders = ordersRepository.findAll();
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        for(int i = 0; i < orderDetails.size(); i++){
            foodOrderDtos.add(
                    new FoodOrderDto(
                            orderDetails.get(i).getName(),
                            orderDetails.get(i).getQuantity(),
                            orderDetails.get(i).getPrice()));
        }
        for(int i = 0; i < orders.size(); i++){
            orderDtos.add(
                    new OrderDto(
                            orders.get(i).getRestaurant().getName(),
                            foodOrderDtos, orders.get(i).getDeliverFee(),
                            orders.get(i).getTotalPrice() + orders.get(i).getDeliverFee()));
        }
        return orderDtos;
    }
}
