package com.skropotov.crm.services;

import java.util.List;

import com.skropotov.crm.models.Product;
import com.skropotov.crm.transfers.ProductDto;

public interface ProductService {
	List<Product> findAllProducts();
	List<Product> findNonDeletedProducts();
	
	Product findOne(Long productId);

	void deleteProduct(Long productId);

	void createProduct(ProductDto productDto);
	
	void editProduct(Long productId, ProductDto productDto);
}
