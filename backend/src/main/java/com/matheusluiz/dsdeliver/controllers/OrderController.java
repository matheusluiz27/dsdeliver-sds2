package com.matheusluiz.dsdeliver.controllers;

import com.matheusluiz.dsdeliver.dto.OrderDTO;
import com.matheusluiz.dsdeliver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> inser(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.insert(orderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id) {
        OrderDTO dto = orderService.setDelivered(id);
        return ResponseEntity.ok().body(dto);
    }
}
