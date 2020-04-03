/**
 * 
 */
package com.product.ecommerce.rest.admin.dao;

import java.util.Map;

/**
 * Interface for Stock DAO
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
public interface StockDao {

	/**
	 * Getting stock count
	 * 
	 * @param productId
	 * @return
	 */
	public Map<String, Object> getStockCount(int productId);

	/**
	 * Updating stock count
	 * 
	 * @param adminId
	 * @param stockUpdateCount
	 * @param productId
	 * @return
	 */
	public int updateStockCount(int adminId, int stockUpdateCount, int productId);
}
