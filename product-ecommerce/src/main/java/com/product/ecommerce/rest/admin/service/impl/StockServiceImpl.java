/**
 * 
 */
package com.product.ecommerce.rest.admin.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.ecommerce.rest.admin.dao.StockDao;
import com.product.ecommerce.rest.admin.model.ProductStock;
import com.product.ecommerce.rest.admin.service.StockService;

/**
 * Implementation class for stock service
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;

	@Override
	public ProductStock getStockCount(int productId) {

		// Get the stock count for the product
		Map<String, Object> stockMap = stockDao.getStockCount(productId);

		ProductStock productStockCount = new ProductStock();

		productStockCount.setProductId(productId);
		productStockCount.setAvailableStock(((BigDecimal) stockMap.get("stockCount")).intValue());

		return productStockCount;
	}

	@Override
	public int updateStock(int adminId, int stockUpdateCount, int productId) {

		// Update the stock count
		int stockUpdate = stockDao.updateStockCount(adminId, stockUpdateCount, productId);

		return stockUpdate;
	}

}
