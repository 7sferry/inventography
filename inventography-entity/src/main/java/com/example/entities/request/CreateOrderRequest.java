/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.entities.request;

import java.io.Serializable;
import java.util.Set;

public record CreateOrderRequest(String customerName, String sellerName,
								 Set<CreateOrderItemRequest> items) implements Serializable{
}
