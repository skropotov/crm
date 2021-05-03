package com.skropotov.crm.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skropotov.crm.services.ProductService;
import com.skropotov.crm.transfers.ProductDto;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<ProductDto> getProducts() {
		return productService.findNonDeletedProducts().stream()
				.map(x -> ProductDto.from(x))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/products/{product-id}")
	public ProductDto getProduct(@PathVariable("product-id") Long productId) {
		return ProductDto.from(productService.findOne(productId));
	}
	
	@DeleteMapping("/products/{product-id}")
	public void deleteProduct(@PathVariable("product-id") Long productId) {
		productService.deleteProduct(productId);
	}
	
	@PostMapping("/products")
	public void createProduct(@RequestBody ProductDto productDto) {
		productService.createProduct(productDto);
	}
	
	@PutMapping("/products/{product-id}")
	public void editProduct(@PathVariable("product-id") Long productId, @RequestBody ProductDto productDto) {
		productService.editProduct(productId, productDto);
	}
}
