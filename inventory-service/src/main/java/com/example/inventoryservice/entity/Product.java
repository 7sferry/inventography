/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@EqualsAndHashCode(of = {"productCode"})
public class Product{
	@Id
	@Column(length = 26)
	private String productCode;
	@Column(length = 50)
	private String productName;
	@Column(name = "is_active")
	private boolean active;
	@Column
	private int stock;
	@Column
	private LocalDate expiringDate;
	@CreationTimestamp(source = SourceType.VM)
	@Column(updatable = false)
	private OffsetDateTime creationTime;
	@UpdateTimestamp(source = SourceType.VM)
	private ZonedDateTime lastUpdatedTime;
	@Version
	private Integer version;

}
