/**
 * 
 */
package com.product.ecommerce.rest.admin.model;

import java.util.List;

import lombok.Data;

/**
 * Product Order for the admin
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Data
public class AdminProduct {

	private int productOrderId;
	private List<AdminOrderItem> adminOrderItems;
}
