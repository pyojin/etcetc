package com.lucy.plants.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucy.plants.dao.ExcelDao;
import com.lucy.plants.dto.ExcelDto;

@Service
public class ExcelService {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private SqlSessionFactory ssfb;
	
	
	//전체목록 불러오기
	public List<ExcelDto> selectAllOrderList() {
		return sqlSession.getMapper(ExcelDao.class).selectAllOrderList();
	}
	
	//엑셀 다운로드
	@Transactional
	public void selectExcelList(HttpServletResponse response, HttpServletRequest request) {
		
		SqlSession sqlSession = ssfb.openSession();
		
	    // 메모리에 100개의 행을 유지합니다. 행의 수가 넘으면 디스크에 적습니다.
	    SXSSFWorkbook wb = new SXSSFWorkbook(100);
	    final Sheet sheet = wb.createSheet();
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    map.put("searchDate", request.getParameter("searchDate"));
	    map.put("startDate", request.getParameter("startDate"));
	    map.put("endDate", request.getParameter("endDate"));
	    map.put("keyword", request.getParameter("keyword"));
	    
	    try {
	        sqlSession.select("selectExcelList", map, new ResultHandler<ExcelDto>() {
	  	    @Override
	    	    public void handleResult(ResultContext<? extends ExcelDto> resultContext) {
	  	    		// 데이터베이스에서 패치된 객체
	  	    		ExcelDto excelDto = resultContext.getResultObject();
	  	    		// 현재 처리중인 행번호(1부터 시작됨.)
	  	    		Row row = sheet.createRow(resultContext.getResultCount() - 1);
		  	      	Cell cell = null;
		  	      	cell = row.createCell(0);
		  	      	cell.setCellValue(excelDto.getSend_name());
		  	        cell = row.createCell(1);
		  	        cell.setCellValue(excelDto.getSend_tel());
		  	        cell = row.createCell(2);
		  	        cell.setCellValue(excelDto.getSend_addr1());
		  	        cell = row.createCell(3);
		  	        cell.setCellValue(excelDto.getGoods_price());
		  	        cell = row.createCell(4);
		  	        cell.setCellValue(excelDto.getSend_post());
	  	    	}
	        });

	    //생성 성공 시 처리 부분
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
		wb.write(response.getOutputStream());

	    } catch(Exception e) {

	    	//파일 생성 실패 시
	    	System.out.println("failed");
	        response.setHeader("Set-Cookie", "fileDownload=false; path=/");
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        response.setHeader("Content-Type","text/html; charset=utf-8");

	        OutputStream out = null;
	        try {
	            out = response.getOutputStream();
	            byte[] data = new String("fail..").getBytes();
	            out.write(data, 0, data.length);
	        } catch(Exception ignore) {
	            ignore.printStackTrace();
	        } finally {
	            if(out != null) try { out.close(); } catch(Exception ignore) {}
	        }

	    } finally {
	    	sqlSession.close();
	        // 디스크 적었던 임시파일을 제거합니다.
	        wb.dispose();
	        try { wb.close(); } catch(Exception ignore) {}
	        System.out.println("end");
	    }


	}
	
}
