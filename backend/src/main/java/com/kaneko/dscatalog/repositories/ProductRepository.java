package com.kaneko.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaneko.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
