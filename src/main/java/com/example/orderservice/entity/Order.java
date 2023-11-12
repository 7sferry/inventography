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
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(of = "orderNo")
public class Order implements Serializable{
	@Id
	@Column(name = "order_no", length = 26)
	private String orderNo;
	@Column(name = "customer_name", length = 25)
	private String customerName;
	@Column(name = "seller_name", length = 25)
	private String sellerName;
	@Column
	private boolean active;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
	private Set<OrderItem> items;
	@CreationTimestamp
	@Column(updatable = false)
	private OffsetDateTime creationTime;
	@UpdateTimestamp
	private OffsetDateTime lastUpdatedTime;
	@Version
	private Integer version;

}
