package com.poly.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
	 @Query("SELECT SUM(od.price * od.quantity) FROM OrderDetail od")
	 Double getTotalAmount();
	    
}
