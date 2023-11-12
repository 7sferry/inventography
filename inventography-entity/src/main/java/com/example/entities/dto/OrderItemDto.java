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

/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.entities.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record OrderItemDto(long id, String productCode, int quantity, BigDecimal price, OffsetDateTime creationTime,
						   OffsetDateTime lastUpdatedTime) implements Serializable{
}
