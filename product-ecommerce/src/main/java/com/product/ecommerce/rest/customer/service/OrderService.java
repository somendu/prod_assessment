/**
 * 
 */
package com.product.ecommerce.rest.customer.service;

import java.util.List;

import com.product.ecommerce.rest.customer.model.ProductWithCount;

/**
 * 
 * Order Service class
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
public interface OrderService {

	/**
	 * Placing order method
	 * 
	 * @param orderedProducts
	 * @param custId
	 * @return
	 */
	public String placeOrder(List<ProductWithCount> orderedProducts, String custId);
}
