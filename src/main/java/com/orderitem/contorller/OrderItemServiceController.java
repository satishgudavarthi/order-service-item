package com.orderitem.contorller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderitem.contorller.client.ProductController;
import com.orderitem.entity.OrderItem;
import com.orderitem.entity.OrderResponse;
import com.orderitem.entity.Product;
import com.orderitem.exception.ProductException;
import com.orderitem.service.OrderItemService;

@RestController()
@RequestMapping("/v1")
public class OrderItemServiceController {
	private static final Logger LOG = LoggerFactory
			.getLogger(OrderItemServiceController.class);
	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductController productContorller;

	@PostMapping("/items")
	public ResponseEntity<Object> addOrder(@RequestBody OrderItem order) {
		order.getItems().stream().forEach(p -> validateProduct(p));
		return ResponseEntity.accepted().body(
				orderItemService.createOrder(order));
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<OrderResponse> fetchOrder(@PathVariable Integer id) {
		if (id <= 0) {
			throw new ProductException("E1001", "Invalid Product Exception");
		}
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setResult(orderItemService.fetchOrder(id));
		return ResponseEntity.accepted().body(orderResponse);
	}

	public void validateProduct(Product product) {
		try {
			ResponseEntity<OrderResponse> orderResponse = productContorller
					.fetchOrder(product.getProductCode());
			if (orderResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				throw new ProductException("O1001", "Product Not Found ::"
						+ product.getProductCode());
			}
		} catch (RuntimeException e) {
			throw new ProductException("O1001", "Product Not Found ::"
					+ product.getProductCode());
		}
	}
}
