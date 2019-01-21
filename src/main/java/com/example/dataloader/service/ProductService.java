package com.example.dataloader.service;

import java.util.List;
import java.util.stream.Stream;

import com.example.dataloader.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    public Stream<Product> getResultSetStream() ;
    
    Product getProductById(Long id);

    Product saveProduct(Product product);
    
    void saveAllProduct(List<Product> products);
    
    public void generateFileUsingStream();
}
