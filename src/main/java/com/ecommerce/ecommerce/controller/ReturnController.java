package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.ReturnRequestDTO;
import com.ecommerce.ecommerce.entity.ReturnRequest;
import com.ecommerce.ecommerce.service.ReturnService;

@RestController
@RequestMapping("/api/returns")
@CrossOrigin(origins = "*")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping
    public ReturnRequest createReturn(
            @RequestBody ReturnRequestDTO dto) {

        return returnService.createReturn(dto);
    }

    @GetMapping
    public List<ReturnRequest> getAllReturns() {

        return returnService.getAllReturns();
    }

    @GetMapping("/user/{userId}")
    public List<ReturnRequest> getUserReturns(
            @PathVariable Long userId) {

        return returnService.getUserReturns(userId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<ReturnRequest> getSellerReturns(
            @PathVariable Long sellerId) {

        return returnService.getSellerReturns(sellerId);
    }

    @PutMapping("/approve/{returnId}")
    public ReturnRequest approveReturn(
            @PathVariable Long returnId) {

        return returnService.approveReturn(returnId);
    }

    @PutMapping("/reject/{returnId}")
    public ReturnRequest rejectReturn(
            @PathVariable Long returnId) {

        return returnService.rejectReturn(returnId);
    }

}