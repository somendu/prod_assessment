package com.product.ecommerce.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.rest.admin.model.AdminOrders;
import com.product.ecommerce.rest.admin.service.AdminOrderService;

/**
 * Admin Controller
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private AdminOrderService adminOrderService;

	@PutMapping("/approveOrder/{adminId}")
	public String approveOrder(@PathVariable String adminId) {

		// Query prod_order table - get order id
		AdminOrders orderId = adminOrderService.getOrdersForAdmin(adminId);
		orderId.setAdminId(adminId);

		// Use order id to set Order
		String messageReturn = adminOrderService.setOrderStock(orderId);

		return messageReturn;
	}

}
