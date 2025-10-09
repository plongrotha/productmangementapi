package com.productmanagement.productmanagementapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.model.entity.OutOfInStockProduct;
import com.productmanagement.productmanagementapi.repository.OutOfInStockProductRepository;
import com.productmanagement.productmanagementapi.service.OutOfInStockProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutOfInStockProductServiceImpl implements OutOfInStockProductService {

    private final OutOfInStockProductRepository inStockProductRepository;

    @Override
    public List<OutOfInStockProduct> allOutOfStock() {
        return Optional.ofNullable(inStockProductRepository.findAll())
                .orElseThrow(() -> new RuntimeException("No out of stock products found"));
    }
}
