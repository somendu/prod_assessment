/**
 * 
 */
package com.product.ecommerce.rest.admin.service;

import java.util.List;

import com.product.ecommerce.rest.admin.model.AdminOrders;
import com.product.ecommerce.rest.admin.model.AdminProduct;

/**
 * Order related with admin
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
public interface AdminOrderService {

	/**
	 * Method to get order list for the admin
	 * 
	 * @param adminId
	 * @return
	 */
	public AdminOrders getOrdersForAdmin(String adminId);

	/**
	 * Setting orders for approval disapproval for the admin
	 * 
	 * @param orderId
	 * @return
	 */
	public String setOrderStock(AdminOrders orderId);

	@Deprecated
	public List<AdminProduct> getOrderItems(AdminOrders orderId);
}
