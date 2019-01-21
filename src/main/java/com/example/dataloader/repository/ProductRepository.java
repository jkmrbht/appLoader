package com.example.dataloader.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Stream<Product> streamAll();
}
