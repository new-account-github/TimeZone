package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account  implements Serializable{
	@Id
    @NotBlank(message = "Username is required")
    String username;
    
    @NotBlank(message = "Password is required")
    String password;
    
    @NotBlank(message = "Fullname is required")
    String fullname;
    
    @Email(message = "Email must be in the correct format")
    String email;
    
    @NotBlank(message = "Phone is required")
    String phone;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;


}
