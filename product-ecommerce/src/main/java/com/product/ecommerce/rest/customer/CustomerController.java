package com.product.ecommerce.rest.customer;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.rest.customer.model.ProdOrder;
import com.product.ecommerce.rest.customer.model.ProductWithCount;
import com.product.ecommerce.rest.customer.service.OrderService;

/**
 * 
 * Customer Controller class
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	protected Logger logger = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	private OrderService orderService;

	@PutMapping("/placeOrder/{custId}")
	public String placeOrder(@RequestBody ProdOrder orderIds, @PathVariable String custId) {

		// The map consist of productid and count for the product
		List<ProductWithCount> orderList = orderIds.getOrderedProducts();

		String orderPlaced = orderService.placeOrder(orderList, custId);

		return "Order Placed";
	}
}
