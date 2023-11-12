/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.entities.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Builder
public record OrderDto(String orderNo, String customerName, String sellerName, boolean active, Set<OrderItemDto> items,
					   OffsetDateTime creationTime, OffsetDateTime lastUpdatedTime) implements Serializable{
}
