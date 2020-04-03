/**
 * 
 */
package com.product.ecommerce.rest.admin.dao;

import java.util.List;
import java.util.Map;

import com.product.ecommerce.rest.admin.model.AdminOrderItem;

/**
 * DAO Layer for Admin order
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
public interface AdminOrderDao {

	/**
	 * Getting order list for admin
	 * 
	 * @param adminId
	 * @return
	 */
	public List<Map<String, Object>> getAdminOrderList(String adminId);

	/**
	 * Getting singled out orders based on order id
	 * 
	 * @param orderIdList
	 * @return
	 */
	public List<Map<String, Object>> getAdminOrderItemList(int orderIdList);

	/**
	 * Approving item
	 * 
	 * @param adminOrderItem
	 * @return
	 */
	public int approveItem(AdminOrderItem adminOrderItem);

	/**
	 * Disapproving item
	 * 
	 * @param adminOrderItem
	 * @return
	 */
	public int disapproveItem(AdminOrderItem adminOrderItem);

}
