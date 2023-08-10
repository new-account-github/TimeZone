package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name= "OrdersStatus")
public class OrderStatus implements Serializable{
	@Id
	Integer id;
	String name;
	@ManyToOne
	@JoinColumn(name = "OrderId")
	Order order;
	
	
}
