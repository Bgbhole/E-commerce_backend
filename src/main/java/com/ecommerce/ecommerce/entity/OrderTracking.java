package com.ecommerce.ecommerce.entity;

	

	import java.time.LocalDateTime;

	import com.ecommerce.ecommerce.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

	@Entity
	@Table(name = "order_tracking")
	public class OrderTracking {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long trackingId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id", nullable = false)
	    @JsonBackReference("tracking")
	    private Order order;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TrackingStatus status;

	    @Column(length = 150)
	    private String location;

	    @Column(length = 500)
	    private String description;

	    private LocalDateTime updatedAt;

	    public OrderTracking() {
	    }

	    @PrePersist
	    public void prePersist() {
	        this.updatedAt = LocalDateTime.now();
	    }

	    public Long getTrackingId() {
	        return trackingId;
	    }

	    public void setTrackingId(Long trackingId) {
	        this.trackingId = trackingId;
	    }

	    public Order getOrder() {
	        return order;
	    }

	    public void setOrder(Order order) {
	        this.order = order;
	    }

	    public TrackingStatus getStatus() {
	        return status;
	    }

	    public void setStatus(TrackingStatus status) {
	        this.status = status;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
	    }
	}
	

