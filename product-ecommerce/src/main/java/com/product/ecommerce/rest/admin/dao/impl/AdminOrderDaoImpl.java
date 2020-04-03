/**
 * 
 */
package com.product.ecommerce.rest.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.ecommerce.rest.admin.dao.AdminOrderDao;
import com.product.ecommerce.rest.admin.model.AdminOrderItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Admin Order implementation class
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminOrderDaoImpl implements AdminOrderDao {

	@Autowired
	private final JdbcTemplate jdbcTemplate;

	private final String adminOrderQuery = "SELECT i_prod_order_id prodOrderId FROM prod_order WHERE i_admin_id = ? "
			+ "AND i_status > 0";

	private final String adminOrderItemQuery = "SELECT i_product_id productId, i_prod_count prodCount "
			+ "FROM order_items WHERE i_prod_order_id = ? AND c_approve_status = 'N' AND i_status > 0";

	private final String adminApproveItemQuery = "UPDATE order_items SET c_approve_status = 'Y', i_status = -1 "
			+ "WHERE i_prod_order_id = ? AND i_product_id = ?";

	private final String adminDisapproveItemQuery = "UPDATE order_items SET c_approve_status = 'N', i_status = 1 "
			+ "WHERE i_prod_order_id = ? AND i_product_id = ?";

	@Override
	public List<Map<String, Object>> getAdminOrderList(String adminId) {

		// Query prod_order table - get order id
		List<Map<String, Object>> adminOrderList = jdbcTemplate.queryForList(adminOrderQuery, adminId);

		return adminOrderList;
	}

	@Override
	public List<Map<String, Object>> getAdminOrderItemList(int orderId) {

		List<Map<String, Object>> adminOrderList = jdbcTemplate.queryForList(adminOrderItemQuery, orderId);

		return adminOrderList;

	}

	@Override
	public int approveItem(AdminOrderItem adminOrderItem) {

		int approvedReturn = jdbcTemplate.update(adminApproveItemQuery, adminOrderItem.getProductOrderId(),
				adminOrderItem.getProductId());

		return approvedReturn;
	}

	@Override
	public int disapproveItem(AdminOrderItem adminOrderItem) {

		int disapprovedReturn = jdbcTemplate.update(adminDisapproveItemQuery, adminOrderItem.getProductOrderId(),
				adminOrderItem.getProductId());

		return disapprovedReturn;
	}
}
