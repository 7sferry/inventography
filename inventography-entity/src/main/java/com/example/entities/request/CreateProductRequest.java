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

package com.example.entities.request;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record CreateProductRequest(String productCode, String productName, int stock,
								   LocalDate expiringDate) implements Serializable{
}
