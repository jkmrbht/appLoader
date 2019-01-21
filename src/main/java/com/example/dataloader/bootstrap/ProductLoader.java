package com.example.dataloader.bootstrap;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.dataloader.domain.Product;
import com.example.dataloader.service.ProductService;

//@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private ProductService productService;

    private Logger log = LoggerFactory.getLogger(ProductLoader.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	Product product = null;
    	List<Product> products = new ArrayList<>(1000);
    	for(int i = 1 ; i < 1000000 ; i++) {
    		product = new Product();
            product.setDescription("Product J-"+i);
            product.setPrice(new BigDecimal(Math.random()));
            product.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
            product.setProductId(UUID.randomUUID().toString());
            products.add(product);
            if(i % 1000 == 0) {
            	log.debug("List products created " + products.size());
            	productService.saveAllProduct(products);
            	products.clear();
            	log.debug("Post save :List products size " + products.size());
            }
    	}
    	if(products.size() > 0) {
    		log.debug("List products created " + products.size());
    		productService.saveAllProduct(products);
    	}
    	
    	
    }
}
