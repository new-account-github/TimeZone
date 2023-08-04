package com.poly.dao.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.OrderDetail;

@Repository
public interface ReportDAO extends JpaRepository<OrderDetail, Long>{
	@Query(
		   value = "SELECT p.id, p.name, sum(od.price), o.createDate "
		          +"FROM OrderDetail od "
				  +"JOIN od.order o "
		          +"JOIN od.product p "
				  +"GROUP BY o.createDate , p.id , p.name "
		          +"ORDER BY o.createDate DESC"
		   )
	List<Object[]> getOrderSummary();

	@Query( 
			value = "SELECT p.id ,p.name, sum(od.price * od.quantity)"
				   +"FROM OrderDetail od "
				   +"JOIN od.order o "
				   +"JOIN od.product p "
				   +"GROUP BY p.id, p.name "
				   +"ORDER BY sum(od.price * od.quantity) DESC"
		  )
	List<Object[]> getRevenue();
	
	@Query(
			value = "SELECT od.product.id,od.product.name, count(od.product.id) "
				   +"FROM OrderDetail od "
				   +"GROUP BY od.product.id,od.product.name "
				   +"ORDER BY count(od.product.id) DESC"
		  )
	List<Object[]> countProductSold();
	
	@Query(
			value = "SELECT od.order.id, od.order.createDate, o.account.username, sum(od.price * od.quantity) "
			       +"FROM OrderDetail od "
				   +"JOIN od.order o "
			       +"GROUP BY od.order.id, od.order.createDate, o.account.username "
			       +"ORDER BY sum(od.price * od.quantity) DESC"
		  )
	List<Object[]> orderTotalByUsername();
	
	@Query(
			value = "SELECT o.account.username, sum(od.quantity), sum(od.price * od.quantity) "
				    +"FROM OrderDetail od "
					+"JOIN od.order o "
				    +"GROUP BY o.account.username "
		   )
	List<Object[]> getAmountOrder();
	
	
	@Query(value = "SELECT count(a.username) FROM Account a")
	String staticalAccount();
	
	@Query(value = "SELECT count(o.id) FROM Order o")
	String staticalOrder();
	
}
