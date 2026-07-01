	package com.ecommerce.ecommerce.repository;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

	import com.ecommerce.ecommerce.entity.Order;
	import com.ecommerce.ecommerce.entity.OrderTracking;

	@Repository
	public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {

	    /**
	     * Returns the complete tracking history of an order
	     * sorted by update time.
	     */
	    List<OrderTracking> findByOrderOrderByUpdatedAtAsc(Order order);

	    /**
	     * Returns tracking history using order id.
	     */
	    List<OrderTracking> findByOrderOrderIdOrderByUpdatedAtAsc(Long orderId);

	    /**
	     * Returns latest tracking entry.
	     */
	    OrderTracking findTopByOrderOrderByUpdatedAtDesc(Order order);

	    /**
	     * Returns latest tracking entry using order id.
	     */
	    OrderTracking findTopByOrderOrderIdOrderByUpdatedAtDesc(Long orderId);

	}