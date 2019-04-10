package com.lucy.plants.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.lucy.plants.dto.ExcelDto;

public class ExcelHandler<T> implements ResultHandler{
	private SXSSFWorkbook wb;
    private SXSSFSheet sheet;
    private SXSSFRow row;
    private SXSSFCell cell;
    private int rowNum;
    private HttpServletResponse response;
    private ResultContext resultContext;
	
    @Override
	public void handleResult(ResultContext resultContext) {

	}
    
    public ExcelHandler(SXSSFWorkbook wb, HttpServletResponse response) {
        this.wb = wb;
        this.response = response;
        System.out.println("생성자~");
        //createTitle();
        ExcelDto data = (ExcelDto)resultContext.getResultObject(); //데이터베이스에서 패치된 객체
        System.out.println("핸들리절트");
		createBody(data);
		
		// 현재 처리중인 행번호(1부터 시작됨.)
    	//row = sheet.createRow(resultContext.getResultCount() - 1);
    }
    
  /*  private void createTitle() {
        int colIdx = 0;
		row = sheet.createRow(rowNum);
		ExcelUtil.createHeaderCell(sheet, row, colIdx++, ExcelViewUtil.pixel2excel(60), "이름", HEADER_STYLE);
		ExcelUtil.createHeaderCell(sheet, row, colIdx++, ExcelViewUtil.pixel2excel(80), "부서명", HEADER_STYLE); 
        // .......
        rowNum++;
    }*/
   
    private void createBody(ExcelDto data) {
    	System.out.println("바디바디~~~");
        int colIdx = 0;
		row = sheet.createRow(rowNum);
		row.createCell(colIdx++).setCellValue(data.getSend_name());
		row.createCell(colIdx++).setCellValue(data.getSend_tel());
		row.createCell(colIdx++).setCellValue(data.getSend_post());
		row.createCell(colIdx++).setCellValue(data.getSend_addr1());
		row.createCell(colIdx++).setCellValue(data.getSend_addr2());
		row.createCell(colIdx++).setCellValue(data.getReceive_name());
		row.createCell(colIdx++).setCellValue(data.getReceive_tel());
		row.createCell(colIdx++).setCellValue(data.getReceive_post());
		row.createCell(colIdx++).setCellValue(data.getReceive_addr1());
		row.createCell(colIdx++).setCellValue(data.getReceive_addr2());
		row.createCell(colIdx++).setCellValue(data.getGoods_name());
		row.createCell(colIdx++).setCellValue(data.getGoods_price());
		row.createCell(colIdx++).setCellValue(data.getPickup_date());
		row.createCell(colIdx++).setCellValue(data.getOrder_date()); 
        rowNum++;
        
        
        /*row = sheet.createRow(resultContext.getResultCount() - 1);
        Cell cell = null;
        cell = row.createCell(0);
        cell.setCellValue(vo.getNum().toString());
        cell = row.createCell(1); cell.setCellValue(vo.getTitle());
        cell = row.createCell(2); cell.setCellValue(vo.getContent()); } }); 
        */
        System.out.println("이제부터 리스폰스를 할 거야");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
        System.out.println("리스폰스 끝이여");
        try {
			wb.write(response.getOutputStream());
			System.out.println("성공");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("실패");
		}

        
    }
    

    
}
