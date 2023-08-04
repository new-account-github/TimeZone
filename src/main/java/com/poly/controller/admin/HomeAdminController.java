package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.report.ReportDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller

public class HomeAdminController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ReportDAO reportDAO;
	
	@RequestMapping("/admin/index")
	public String show() {
		return "admin/DashBoard";
	}


	@RequestMapping("/admin/Authority")
	public String EditProduct() {
		return "admin/Authority";
	}

	
	@GetMapping("/admin/index/{id}")
	public Product getOne(@PathVariable("id") Integer id) {	
		return productService.findById(id);
	}
	
	@RequestMapping("/admin/report")
	public String getReport(Model model) {
		List<Object[]> reportResult = reportDAO.getOrderSummary();
		model.addAttribute("results", reportResult);
		List<Object[]> getRevenue = reportDAO.getRevenue();
		model.addAttribute("revenue", getRevenue);
		List<Object[]> quantity = reportDAO.countProductSold();
		model.addAttribute("quantity", quantity);
		List<Object[]> order = reportDAO.orderTotalByUsername();
		model.addAttribute("order", order);
		List<Object[]> getAmout = reportDAO.getAmountOrder();
		model.addAttribute("amount", getAmout);
		String staticalAccount = reportDAO.staticalAccount();
		model.addAttribute("statical", staticalAccount);
		String totalOrder = reportDAO.staticalOrder();
		model.addAttribute("totalOrder", totalOrder);
		return "admin/report";
	}
}
