/**
 * 
 */
package com.product.ecommerce.rest.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.ecommerce.rest.admin.dao.AdminOrderDao;
import com.product.ecommerce.rest.admin.model.AdminOrderItem;
import com.product.ecommerce.rest.admin.model.AdminOrders;
import com.product.ecommerce.rest.admin.model.AdminProduct;
import com.product.ecommerce.rest.admin.model.ProductStock;
import com.product.ecommerce.rest.admin.service.AdminOrderService;
import com.product.ecommerce.rest.admin.service.StockService;

/**
 * Implementation class for admin order
 * 
 * @author Somendu
 * @since Apr 1, 2020
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private AdminOrderDao adminOrderDao;

	@Autowired
	private StockService stockService;

	@Override
	public AdminOrders getOrdersForAdmin(String adminId) {

		AdminOrders adminOrders = new AdminOrders();

		// Getting all the orders for the particular admin
		List<Map<String, Object>> adminOrderList = adminOrderDao.getAdminOrderList(adminId);

		List<Integer> orderIntList = new ArrayList<Integer>();

		for (Map<String, Object> adminOrderMap : adminOrderList) {
			int orderId = ((BigDecimal) adminOrderMap.get("prodOrderId")).intValue();
			orderIntList.add(orderId);
		}

		adminOrders.setOrderIdList(orderIntList);

		return adminOrders;
	}

	@Override
	public String setOrderStock(AdminOrders orderId) {

		String messageReturn = "";

		StringBuilder messageReturnBuilder = new StringBuilder();

		List<Integer> orderIdList = orderId.getOrderIdList();

		for (int orderIdInt : orderIdList) {
			List<Map<String, Object>> productCountMapList = adminOrderDao.getAdminOrderItemList(orderIdInt);

			for (Map<String, Object> productCountMap : productCountMapList) {
				int productId = ((BigDecimal) productCountMap.get("productId")).intValue();
				int prodCount = ((BigDecimal) productCountMap.get("prodCount")).intValue();

				// Query stock table for stock available
				ProductStock productStockCount = stockService.getStockCount(productId);

				int stockCount = productStockCount.getAvailableStock();

				AdminOrderItem adminOrderItem = new AdminOrderItem();
				adminOrderItem.setProductOrderId(orderIdInt);
				adminOrderItem.setProductId(productId);

				if (stockCount >= prodCount) {

					// if stock > prodcount update order_items table b_approve = Y
					int updatedApprove = approve(adminOrderItem);

					messageReturnBuilder
							.append("Approved for product id : " + productId + " For order ID : " + orderIdInt);
					messageReturnBuilder.append(System.getProperty("line.separator"));
					int adminId = Integer.parseInt(orderId.getAdminId());

					// Update stock table with count < prodcount order
					int updateStock = stockService.updateStock(adminId, (stockCount - prodCount), productId);

				} else if (stockCount < prodCount) {

					// if stock < prodCount update orde_item table b_approve = N
					int updatedApprove = disapprove(adminOrderItem);

					messageReturnBuilder
							.append("Disapproved for product id : " + productId + " For order ID : " + orderIdInt);
					messageReturnBuilder.append(System.getProperty("line.separator"));

				}
			}

		}

		messageReturn = messageReturnBuilder.toString();

		return messageReturn;

	}

	/**
	 * Approving the order
	 * 
	 * @param adminOrderItem
	 * @return
	 */
	private int approve(AdminOrderItem adminOrderItem) {

		int approveReturn = adminOrderDao.approveItem(adminOrderItem);

		return approveReturn;
	}

	/**
	 * Disapproving the order
	 * 
	 * @param adminOrderItem
	 * @return
	 */
	private int disapprove(AdminOrderItem adminOrderItem) {

		int disapproveReturn = adminOrderDao.disapproveItem(adminOrderItem);

		return disapproveReturn;
	}

	@Deprecated
	@Override
	public List<AdminProduct> getOrderItems(AdminOrders orderId) {

		// StockService
		// TODO Check order_stock table with product id and count to check count
		// if above prod count is less than stock count
		// List<ProductStockCount> productStockCountList =
		// stockService.getStockCount(adminOrderItemsList);

		List<Integer> orderIdList = orderId.getOrderIdList();

		List<AdminProduct> adminProductList = new ArrayList<AdminProduct>();

		List<Map<String, Object>> productCountMapList = new ArrayList<Map<String, Object>>();

		for (int orderIdInt : orderIdList) {
			List<Map<String, Object>> productCountMapInnerList = adminOrderDao.getAdminOrderItemList(orderIdInt);
			productCountMapList.addAll(productCountMapInnerList);
		}

		List<AdminOrderItem> adminOrderItemList = new ArrayList<AdminOrderItem>();

		int oldProdOrderId = 0;
		for (Map<String, Object> productCountMap : productCountMapList) {

			int productOrderId = ((BigDecimal) productCountMap.get("productOrderId")).intValue();

			int productId = ((BigDecimal) productCountMap.get("productId")).intValue();
			int prodCount = ((BigDecimal) productCountMap.get("prodCount")).intValue();

			AdminProduct adminProduct = new AdminProduct();

			if (oldProdOrderId != productOrderId) {

				adminProduct.setProductOrderId(productOrderId);
				adminProductList.add(adminProduct);

			}

			AdminOrderItem adminOrderItem = new AdminOrderItem();
			adminOrderItem.setProductOrderId(productOrderId);
			adminOrderItem.setProductId(productId);
			adminOrderItem.setProductCount(prodCount);

			adminOrderItemList.add(adminOrderItem);

			oldProdOrderId = productOrderId;

		}

		for (AdminProduct adminProduct : adminProductList) {

			List<AdminOrderItem> adminOrderInnerItemList = new ArrayList<AdminOrderItem>();

			for (AdminOrderItem adminOrderItem : adminOrderItemList) {
				if (adminProduct.getProductOrderId() == adminOrderItem.getProductOrderId()) {
					AdminOrderItem adminOrderInnerItem = new AdminOrderItem();
					adminOrderInnerItem.setProductId(adminOrderItem.getProductId());
					adminOrderInnerItem.setProductCount(adminOrderItem.getProductCount());
					adminOrderInnerItemList.add(adminOrderInnerItem);
					adminProduct.setAdminOrderItems(adminOrderInnerItemList);
				}
			}
		}

		return adminProductList;
	}
}
