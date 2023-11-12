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
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Builder
public record ProductDto(String productCode, String productName, boolean active, int stock, LocalDate expiringDate,
						 OffsetDateTime creationTime, ZonedDateTime lastUpdatedTime) implements Serializable{
}
