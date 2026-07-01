package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

import com.ecommerce.ecommerce.enums.TrackingStatus;

public class TrackingTimelineDTO {

    private TrackingStatus status;

    private String location;

    private String description;

    private LocalDateTime updatedAt;

    public TrackingTimelineDTO() {
    }

    public TrackingTimelineDTO(TrackingStatus status,
                               String location,
                               String description,
                               LocalDateTime updatedAt) {
        this.status = status;
        this.location = location;
        this.description = description;
        this.updatedAt = updatedAt;
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