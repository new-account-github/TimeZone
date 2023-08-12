package com.poly.dao.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.OrderDetail;

@Repository
public interface ReportDAO extends JpaRepository<OrderDetail, Long> {

	@Query(value = "SELECT p.id ,p.name, sum(od.price * od.quantity)"
			+ "FROM OrderDetail od "
			+ "JOIN od.product p "
			+ "GROUP BY p.id, p.name "
			+ "ORDER BY sum(od.price * od.quantity) DESC")
	List<Object[]> getRevenuePerProduct();

	@Query(value = "SELECT p.id, p.name, sum(od.quantity) "
			+ "FROM OrderDetail od "
			+ "JOIN od.product p "
			+ "GROUP BY p.id, p.name "
			+ "ORDER BY count(od.product.id) DESC")
	List<Object[]> countProductSold();

	@Query(value = "SELECT o.account.username, o.account.fullname, sum(od.quantity), sum(od.price * od.quantity) "
			+ "FROM OrderDetail od "
			+ "JOIN od.order o "
			+ "GROUP BY o.account.username,o.account.fullname ")
	List<Object[]> getTotalAmountPaid();

	@Query(value = "SELECT o.createDate, sum(od.quantity * od.price) "
			+ "FROM OrderDetail od "
			+ "JOIN od.order o "
			+ "GROUP BY o.createDate")
	List<Object[]> getRevenue();

	@Query(value = "SELECT count(a.username) FROM Account a")
	String totalAccount();

	@Query(value = "SELECT count(o.id) FROM Order o")
	String totalOrder();

	@Query(value = "SELECT sum(od.price * od.quantity) FROM OrderDetail od")
	Double totalRevenue();

	@Query(value = "SELECT sum(od.quantity) FROM OrderDetail od")
	Integer totalProductHasSold();
}
