package com.lucy.plants.dao;

import java.util.List;
import java.util.Map;

import com.lucy.plants.dto.ExcelDto;

public interface ExcelDao {

	public List<ExcelDto> selectAllOrderList();
	
	public List<ExcelDto> selectExcelList(Map<String, Object> map);

	public List<ExcelDto> selectExcelList(Map<String, Object> map);

}
