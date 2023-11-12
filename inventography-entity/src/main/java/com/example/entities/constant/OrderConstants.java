/************************
 * Made by [MR Ferry™]  *
 * on November 2023     *
 ************************/

package com.example.entities.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConstants{
	public static final String ORDERING_TOPIC = "ordering";
	public static final String ORDERING_ROLLBACK_TOPIC = "ordering-rollback";
	public static final String ORDER_GROUP = "order-group";
	public static final String PRODUCT_GROUP = "product-group";
}
