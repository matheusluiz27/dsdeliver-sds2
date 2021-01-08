package com.matheusluiz.dsdeliver.controllers;

import com.matheusluiz.dsdeliver.dto.OrderDTO;
import com.matheusluiz.dsdeliver.dto.ProductDTO;
import com.matheusluiz.dsdeliver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService OrderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = OrderService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
