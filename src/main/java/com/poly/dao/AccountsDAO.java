package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;

public interface AccountsDAO extends JpaRepository<Account, String> {
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.id.role IN ('DIR','STA','AD')")
	List<Account> getAdmin();
<<<<<<< HEAD
=======

	Optional<Account> findByUsernameAndEmail(String username, String email);
	
	Optional<Account> findByUsername(String username);
>>>>>>> eafe7e65e3de44fceeedcc8501c3e71259729d92
	
}
