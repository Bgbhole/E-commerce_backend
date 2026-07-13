package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.ReturnRequestDTO;
import com.ecommerce.ecommerce.entity.ReturnRequest;

public interface ReturnService {

    ReturnRequest createReturn(ReturnRequestDTO dto);

    List<ReturnRequest> getAllReturns();

    List<ReturnRequest> getUserReturns(Long userId);

    List<ReturnRequest> getSellerReturns(Long sellerId);

    ReturnRequest approveReturn(Long returnId);

    ReturnRequest rejectReturn(Long returnId);

}