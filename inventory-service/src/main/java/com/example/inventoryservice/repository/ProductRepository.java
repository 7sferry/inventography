/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, String>{
	Set<Product> findAllByActiveIsTrueAndProductCodeIn(Collection<String> productCodes);
}
