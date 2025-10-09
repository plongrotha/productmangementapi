package com.productmanagement.productmanagementapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.productmanagement.productmanagementapi.mapper.OrderMapper;
import com.productmanagement.productmanagementapi.model.dto.OrderItemRequest;
import com.productmanagement.productmanagementapi.model.dto.OrderRequest;
import com.productmanagement.productmanagementapi.model.entity.*;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;
import com.productmanagement.productmanagementapi.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
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
    private final OutOfInStockProductRepository outOfInStockProductRepository;


    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItems()) {

            Product product = productRepository.findById(orderItemRequest.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));

            if (!product.isInStock()) {
                throw new NotFoundException("Product is not in stock");
            }

            if (product.getQuantity() < orderItemRequest.getQuantity()) {
                throw new NotFoundException("Insufficient stock for product: " + product.getProductId() +
                        ". Available: " + product.getQuantity() +
                        ", Requested: " + orderItemRequest.getQuantity());
            }

            // set orderItem and save
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPricePerUnit(product.getPrice());
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));
            orderItem.setTotalPrice(totalPrice);

            // add orderItem to list
            orderItems.add(orderItem);
            orderItemRepsitory.save(orderItem);

            product.setQuantity(product.getQuantity() - orderItemRequest.getQuantity());
            productRepository.save(product);

            if (product.getQuantity() == 0) {
                OutOfInStockProduct outOfInStockProduct = new OutOfInStockProduct();
                outOfInStockProduct.setProduct(product);
                outOfInStockProduct.setNotes("product is ran out of stock");
                outOfInStockProduct.setOutStockDate(LocalDateTime.now());
                outOfInStockProductRepository.save(outOfInStockProduct);
            }

            totalAmount = totalAmount.add(totalPrice);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new NotFoundException("No orders found");
        }
        return orders;
    }
}
