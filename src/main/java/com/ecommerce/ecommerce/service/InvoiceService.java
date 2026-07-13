package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.InvoiceDTO;

public interface InvoiceService {

    InvoiceDTO getInvoice(Long orderId);

}