/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.orderservice.mapper;

import com.example.entities.dto.OrderDto;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper{
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	OrderDto oderToOrderDto(Order car);

}
