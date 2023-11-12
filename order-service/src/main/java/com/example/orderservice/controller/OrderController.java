/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.orderservice.controller;

import com.example.entities.constant.OrderConstants;
import com.example.entities.dto.OrderDto;
import com.example.entities.request.CreateOrderItemRequest;
import com.example.entities.request.CreateOrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.example.utils.ULIDS;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class OrderController{
	private final OrderRepository orderRepository;
	private final KafkaOperations<String, Object> kafkaOperations;

	@PostMapping("createOrder")
	public OrderDto create(@RequestBody CreateOrderRequest orderRequest){
		Order entity = new Order();
		entity.setOrderNo(ULIDS.nextULID());
		entity.setActive(true);
		entity.setCustomerName(orderRequest.customerName());
		entity.setSellerName(orderRequest.sellerName());
		Set<CreateOrderItemRequest> requestedItems = orderRequest.items();
		Set<OrderItem> items = LinkedHashSet.newLinkedHashSet(requestedItems.size());
		for(CreateOrderItemRequest requestedItem : requestedItems){
			OrderItem item = new OrderItem();
			item.setPrice(requestedItem.price());
			item.setQuantity(requestedItem.quantity());
			item.setProductCode(requestedItem.productCode());
			item.setOrder(entity);
			items.add(item);
		}
		entity.setItems(items);
		Order save = orderRepository.save(entity);
		OrderDto orderDto = OrderMapper.INSTANCE.oderToOrderDto(save);
		kafkaOperations.send(OrderConstants.ORDERING_TOPIC, orderDto)
				.whenCompleteAsync((stringObjectSendResult, throwable) -> {
					if(throwable != null){
						rollbackOrder(orderDto);
					}
				});
		return orderDto;
	}

	@KafkaListener(groupId = OrderConstants.ORDER_GROUP, topics = {OrderConstants.ORDERING_ROLLBACK_TOPIC})
	public void rollbackOrder(OrderDto orderDto){
		Order order = orderRepository.findFirstByActiveIsTrueAndOrderNo(orderDto.orderNo())
				.map(o -> {
					o.setActive(false);
					return o;
				}).orElseThrow();
		orderRepository.save(order);
	}

}
