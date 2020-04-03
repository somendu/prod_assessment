/**
 * 
 */
package com.product.ecommerce.rest.admin.model;

import java.util.List;

import lombok.Data;

/**
 * Order ID's for the admin
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Data
public class AdminOrders {

	private String adminId;
	private List<Integer> orderIdList;

}
