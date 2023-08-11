package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

		@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
		List<Authority> autoritiesOf(List<Account> accounts);

		@Query(value= "SELECT a.username, a.fullname, a.email, a.phone, b.role.name "
				+ "FROM Authority b "
				+ "JOIN b.account a "
				+ "JOIN b.role  c "
				+ "WHERE b.id.role IN ('STA')")
		List<Object[]> getStaff();
}
