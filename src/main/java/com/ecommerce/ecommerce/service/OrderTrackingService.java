package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.TrackingResponse;
import com.ecommerce.ecommerce.entity.OrderTracking;
import com.ecommerce.ecommerce.enums.TrackingStatus;

public interface OrderTrackingService {

    /**
     * Add a new tracking event for an order.
     *
     * @param orderId      Order ID
     * @param status       Tracking Status
     * @param location     Current Location
     * @param description  Status Description
     * @return Saved OrderTracking
     */
    OrderTracking addTracking(Long orderId,
                              TrackingStatus status,
                              String location,
                              String description);

    /**
     * Returns complete tracking details for customer.
     *
     * @param orderId
     * @return TrackingResponse
     */
    TrackingResponse getTrackingDetails(Long orderId);

    /**
     * Returns complete tracking history.
     *
     * @param orderId
     * @return List<OrderTracking>
     */
    List<OrderTracking> getTrackingHistory(Long orderId);

    /**
     * Returns latest tracking status.
     *
     * @param orderId
     * @return OrderTracking
     */
    OrderTracking getLatestTracking(Long orderId);

}