package com.lucy.plants.dao;

import java.util.List;

import com.lucy.plants.dto.ExcelDto;

public interface ExcelDao {

	public List<ExcelDto> selectAllOrderList();
	
}
