/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.inventoryservice.mapper;

import com.example.entities.dto.ProductDto;
import com.example.inventoryservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper{
	InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

	ProductDto productToProductDto(Product car);
}
