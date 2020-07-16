package com.orderitem.contorller.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderitem.entity.OrderResponse;
import com.orderitem.entity.Product;

@FeignClient(value = "orders", url = "http://localhost:9091/v1/")
public interface ProductController {

	@PostMapping("/orders")
	public ResponseEntity<OrderResponse> createOrder(
			@RequestBody @Valid Product product);

	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderResponse> fetchOrder(
			@PathVariable("id") String productCode);

}
