/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.inventoryservice.controller;

import com.example.entities.constant.OrderConstants;
import com.example.entities.dto.OrderDto;
import com.example.entities.dto.OrderItemDto;
import com.example.entities.dto.ProductDto;
import com.example.entities.request.CreateProductRequest;
import com.example.inventoryservice.entity.Product;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.example.utils.ULIDS;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController{
	private final ProductRepository productRepository;

	@Transactional
	@PostMapping("addProduct")
	public ProductDto addProduct(@RequestBody CreateProductRequest product){
		Product entity = new Product();
		entity.setProductCode(product.productCode() != null ? product.productCode() : ULIDS.nextULID());
		entity.setProductName(product.productName());
		entity.setActive(true);
		entity.setStock(product.stock());
		entity.setExpiringDate(product.expiringDate());
		Product save = productRepository.save(entity);
		return InventoryMapper.INSTANCE.productToProductDto(save);
	}

	@Transactional
	@KafkaListener(groupId = OrderConstants.PRODUCT_GROUP, topics = {OrderConstants.ORDERING_TOPIC}, errorHandler = "rollbackOrder")
	public void updateStock(OrderDto orderDto){
		Set<OrderItemDto> items = orderDto.items();
		Set<String> productCodes = items.stream().map(OrderItemDto::productCode).collect(Collectors.toSet());
		Map<String, Product> productByCode = productRepository.findAllByActiveIsTrueAndProductCodeIn(productCodes).stream().collect(Collectors.toMap(Product::getProductCode, Function.identity()));
		for(OrderItemDto item : items){
			Product product = productByCode.get(item.productCode());
			if(product == null){
				throw new UnsupportedOperationException("product can't be null");
			}
			int currentStock = product.getStock();
			if(item.quantity() > currentStock){
				throw new UnsupportedOperationException("insufficient stock");
			}
			currentStock -= item.quantity();
			product.setStock(currentStock);
			productRepository.save(product);
		}
	}

}
