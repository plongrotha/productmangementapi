package com.productmanagement.productmanagementapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.productmanagement.productmanagementapi.mapper.OrderMapper;
import com.productmanagement.productmanagementapi.model.dto.OrderItemRequest;
import com.productmanagement.productmanagementapi.model.dto.OrderRequest;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;
import com.productmanagement.productmanagementapi.repository.OrderItemRepsitory;
import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.model.entity.Customer;
import com.productmanagement.productmanagementapi.model.entity.Order;
import com.productmanagement.productmanagementapi.model.entity.OrderItem;
import com.productmanagement.productmanagementapi.repository.CustomerRepository;
import com.productmanagement.productmanagementapi.repository.OrderRepository;
import com.productmanagement.productmanagementapi.repository.ProductRepository;
import com.productmanagement.productmanagementapi.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepsitory orderItemRepsitory;
    private final OrderMapper orderMapper;



    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->new NotFoundException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(OrderItemRequest orderItemRequest : orderRequest.getOrderItems()){
            Product product = productRepository.findById(orderItemRequest.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));

            if (!product.isInStock()){
                throw new NotFoundException("Product is not in stock");
            }
            if (product.getQuantity() < orderItemRequest.getQuantity()){
                throw new NotFoundException("Insufficient stock for product: " + product.getProductName() +
                        ". Available: " + product.getQuantity() +
                        ", Requested: " + orderItemRequest.getQuantity());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPricePerUnit(product.getPrice());

            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));

            orderItem.setTotalPrice(totalPrice);

            orderItems.add(orderItem);
            orderItemRepsitory.save(orderItem);

            product.setQuantity(orderItemRequest.getQuantity() - product.getQuantity());
            productRepository.save(product);

            totalAmount = totalAmount.add(totalPrice);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(savedOrder);
    }
}
