package com.poly.service;

import java.util.List;

import com.poly.entity.Authority;
import com.poly.entity.Product;

public interface AuthorityService {

	List<Authority> getAdmin();

	List<Authority> findALL();

	Authority create(Authority authority);

	void delete(Integer id);

	List<Object[]> getStaff();
	
	
}
