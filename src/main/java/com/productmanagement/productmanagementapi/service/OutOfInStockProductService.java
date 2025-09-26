package com.productmanagement.productmanagementapi.service;

import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.OutOfInStockProduct;

public interface OutOfInStockProductService {

    List<OutOfInStockProduct> allOutOfStock();

}
