package com.lucy.plants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucy.plants.dto.ExcelDto;
import com.lucy.plants.service.ExcelService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String openPage() {
		return "excel";
	}
	
	@RequestMapping(value = "/selectAllOrderList", method = RequestMethod.POST)
	public @ResponseBody List<ExcelDto> selectAllOrderList() {
		List<ExcelDto> data = excelService.selectAllOrderList();
		return data;
	}
	
}
