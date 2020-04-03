/**
 * 
 */
package com.product.ecommerce.rest.customer.dao;

import java.util.List;

import com.product.ecommerce.rest.customer.model.AdminDetail;
import com.product.ecommerce.rest.customer.model.ProdOrder;
import com.product.ecommerce.rest.customer.model.ProductWithCount;

/**
 * DAO Layer interface for order by customer
 * 
 * @author Somendu
 * @since Mar 31, 2020
 */
public interface OrderDao {

	/**
	 * To get the admin details
	 * 
	 * @return
	 */
	public AdminDetail getAdminDetail();

	/**
	 * To get the order id
	 * 
	 * @param prodOrder
	 * @return
	 */
	public int getOrderId(ProdOrder prodOrder);

	/**
	 * To insert the order item
	 * 
	 * @param orderId
	 * @param adminName
	 * @param orderList
	 * @return
	 */
	public int insertOrderItem(int orderId, String adminName, List<ProductWithCount> orderList);
}
