package com.skropotov.crm.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skropotov.crm.models.Product;
import com.skropotov.crm.models.Status;
import com.skropotov.crm.repositories.ProductRepository;
import com.skropotov.crm.transfers.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired 
	ProductRepository productRepository;
	
	@Override
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> findNonDeletedProducts() {
		return productRepository.findNonDeletedProducts();
	}

	@Override
	public Product findOne(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = findOne(productId);
		if (product.getStatus() == Status.DELETED) {
			throw new PersistenceException("Product already deleted");
		}
		product.setStatus(Status.DELETED);
		productRepository.save(product);
	}

	@Override
	public void createProduct(ProductDto productDto) {
		Product product = productDto.toProduct();
		productRepository.save(product);
	}

	@Override
	public void editProduct(Long productId, ProductDto productDto) {
		Product savedProduct = findOne(productId);
		
		Product product = productDto.toProduct();
		product.setId(productId);
		product.setCreatedBy(savedProduct.getCreatedBy());
		product.setCreated(savedProduct.getCreated());
		product.setVersion(productDto.getVersion());
		
		productRepository.save(product);
	}

}
