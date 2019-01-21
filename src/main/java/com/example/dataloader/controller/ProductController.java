package com.example.dataloader.controller;


import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataloader.bootstrap.ProductLoader;
import com.example.dataloader.domain.Product;
import com.example.dataloader.service.FileService;
import com.example.dataloader.service.ProductService;

@RestController
public class ProductController {
	 private Logger log = LoggerFactory.getLogger(ProductLoader.class);
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/productsStream", method = RequestMethod.GET, produces="application/json")
    public String productsStream(){
        System.out.println("Returning products:");
        Stream<Product> stream  = productService.getResultSetStream();
        stream.parallel().map(t->t.toString()).forEach(ProductController::eachRecord);
        return "generate File";
    }
    
    private static void eachRecord(String str) {
    	
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json")
    public String products(){
        System.out.println("Returning products:");
        FileService.createFileWithoutStream(productService.listAllProducts());
        return "generate File";
    }

}
