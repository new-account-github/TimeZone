package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	   @Query("SELECT p.id as ProductID, "
			+"p.name as ProductName, "
			+"SUM(od.price * od.quantity) as Revenue "
	   		+"FROM Product p JOIN OrderDetail od "
			+"GROUP BY p.id,p.name "
	   		+"ORDER BY Revenue DESC"
			) 
	   List<Object[]> getProductRevenue();
	    
}
