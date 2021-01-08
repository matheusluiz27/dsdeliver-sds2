package com.matheusluiz.dsdeliver.services;

import com.matheusluiz.dsdeliver.dto.OrderDTO;
import com.matheusluiz.dsdeliver.dto.ProductDTO;
import com.matheusluiz.dsdeliver.entities.Order;
import com.matheusluiz.dsdeliver.entities.OrderStatus;
import com.matheusluiz.dsdeliver.entities.Product;
import com.matheusluiz.dsdeliver.repositories.OrderRepository;
import com.matheusluiz.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> list = orderRepository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
                OrderStatus.PENDING);

        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}
