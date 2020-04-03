/**
 * 
 */
package com.product.ecommerce.rest.admin.service;

import com.product.ecommerce.rest.admin.model.ProductStock;

/**
 * Stock interface
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
public interface StockService {

	/***
	 * Getting the actual stock count from stock
	 * 
	 * @param productId
	 * @return
	 */
	public ProductStock getStockCount(int productId);

	/**
	 * Update the stock count after completion of order
	 * 
	 * @param adminId
	 * @param stockUpdateCount
	 * @param productId
	 * @return
	 */
	public int updateStock(int adminId, int stockUpdateCount, int productId);
}
