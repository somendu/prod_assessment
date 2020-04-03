/**
 * 
 */
package com.product.ecommerce.rest.admin.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.ecommerce.rest.admin.dao.StockDao;

/**
 * Implementation class for stock
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Component
public class StockDaoImpl implements StockDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String stockCountQuery = "SELECT i_count stockCount FROM order_stock WHERE i_product_id = ? AND i_status > 0";

	private final String stockUpdateQuery = "UPDATE order_stock SET i_admin_id = ?, i_count = ? "
			+ "WHERE i_product_id = ?";

	@Override
	public Map<String, Object> getStockCount(int productId) {

		Map<String, Object> stockCountMap = jdbcTemplate.queryForMap(stockCountQuery, productId);

		return stockCountMap;
	}

	@Override
	public int updateStockCount(int adminId, int stockUpdateCount, int productId) {

		int updateStock = jdbcTemplate.update(stockUpdateQuery, adminId, stockUpdateCount, productId);

		return updateStock;
	}

}
