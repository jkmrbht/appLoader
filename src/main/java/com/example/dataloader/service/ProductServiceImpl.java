package com.example.dataloader.service;


import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.dataloader.bootstrap.ProductLoader;
import com.example.dataloader.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@PersistenceContext
	private EntityManager em;
	
	 private Logger log = LoggerFactory.getLogger(ProductLoader.class);
	
	@Override
    public Iterable<Product> listAllProducts() {
        return em.createNamedQuery("query_find_all_product", Product.class).getResultList();
		//return productRepository.findAll();
    }
	
	@Override
    public Stream<Product> getResultSetStream() {
		return em.createNamedQuery("query_find_all_product", Product.class).setFirstResult(0).getResultStream();
		//return productRepository.findAll();
    }
	
	@Override
    public void generateFileUsingStream() {
		FileService.createFileWithStream(em.createNamedQuery("query_find_all_product", Product.class).setFirstResult(0).getResultStream());
		//return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return em.find(Product.class, id);
    	//return productRepository.getOne(id);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        //return productRepository.save(product);
    	em.persist(product);
    	return product ; 
    }
    @Override
    @Transactional
    public void saveAllProduct(List<Product> products) {
       //products.stream().forEach(pr -> em.persist(pr));
    	for(Product pr : products)
    			save(pr);
       em.flush();
       em.clear();
    }
    public Product save(Product product) {
    	em.persist(product);
    	return product ; 
    }
    
}
