package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.VerificationToken;

public interface VerificationTokenDAO extends JpaRepository<VerificationToken, Integer>{
	public VerificationToken findByToken(String token);
}
