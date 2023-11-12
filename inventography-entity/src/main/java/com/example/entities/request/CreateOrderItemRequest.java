/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.entities.request;

import java.io.Serializable;
import java.math.BigDecimal;

public record CreateOrderItemRequest(String productCode, int quantity, BigDecimal price) implements Serializable{
}
