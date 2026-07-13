package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.InvoiceDTO;
import com.ecommerce.ecommerce.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "*")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{orderId}")
    public InvoiceDTO getInvoice(
            @PathVariable Long orderId) {

        return invoiceService.getInvoice(orderId);

    }

}