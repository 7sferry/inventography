/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OrderItem implements Serializable{
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	@Column(name = "product_code", length = 26)
	private String productCode;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price", scale = 2)
	private BigDecimal price;
	@CreationTimestamp
	@Column(updatable = false)
	private OffsetDateTime creationTime;
	@UpdateTimestamp
	private OffsetDateTime lastUpdatedTime;

}
