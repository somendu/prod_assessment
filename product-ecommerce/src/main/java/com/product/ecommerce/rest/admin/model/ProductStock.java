/**
 * 
 */
package com.product.ecommerce.rest.admin.model;

import lombok.Data;

/**
 * Available stock count
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
@Data
public class ProductStock {

	private int productId;
	private int availableStock;
}
