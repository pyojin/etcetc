package com.lucy.plants.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.plants.dao.ExcelDao;
import com.lucy.plants.dto.ExcelDto;

@Service
public class ExcelService {

	@Autowired
	private SqlSession sqlSession;
	
	
	//전체목록 불러오기
	public List<ExcelDto> selectAllOrderList() {
		return sqlSession.getMapper(ExcelDao.class).selectAllOrderList();
	}
	
}
