package com.event.demo.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class ExcelUtil {
	
	public static void setColumnAutoSize(HSSFSheet sheet,int columnSize){
		for(int i = 0 ; i < columnSize; i++){
			sheet.autoSizeColumn(i);
			int columnWidth = sheet.getColumnWidth(i);
			sheet.setColumnWidth(i,columnWidth+20);
		}
	}
	
	public static void setRowCellTitle(HSSFSheet sheet, int rowIndex, int cellNumber, List<String> value,HSSFCellStyle rowStyle,HSSFCellStyle cellStyle) {  
		//sheet.setColumnWidth(0, 250);
		HSSFRow  hSSFRow  = sheet.getRow(rowIndex);
		//hSSFRow.setHeight((short)35);
		hSSFRow.setRowStyle(rowStyle);
		for(int i = 0 ; i < cellNumber; i++){
			HSSFCell  hSSFCell = hSSFRow.getCell(i);
			hSSFCell.setCellStyle(cellStyle);
			hSSFCell.setCellValue(new HSSFRichTextString(value.get(i)));  
		}
    }

	public static void setRowCell(HSSFSheet sheet, int rowIndex, int cellIndex, Object value,HSSFCellStyle rowStyle,HSSFCellStyle cellStyle) {  
		HSSFRow  hSSFRow  = sheet.getRow(rowIndex);
		//hSSFRow.setHeight((short)20);
		hSSFRow.setRowStyle(rowStyle);
		HSSFCell  hSSFCell = hSSFRow.getCell(cellIndex);
		hSSFCell.setCellStyle(cellStyle);
		if(value instanceof String){
			hSSFCell.setCellValue(new HSSFRichTextString(value.toString()));  
		}
		if(value instanceof Integer){
			hSSFCell.setCellValue(value.toString());  
		}
		if(value instanceof Float){
			hSSFCell.setCellValue(((Float) value).doubleValue()); 
		}
		if(value instanceof Double){
			hSSFCell.setCellValue(((Double) value).doubleValue()); 
		}
    }
	
	public static HSSFSheet createSheet(HSSFWorkbook workbook,String sheetName,int rowsNum,int cellsNum){
		HSSFSheet sheet = workbook.createSheet(sheetName);
		for(int i = 0 ; i < rowsNum; i++){
			HSSFRow row = sheet.createRow(i);    
			for(int j = 0 ; j < cellsNum; j++){
				HSSFCell cell = row.createCell(j);  
			}
		}
		return sheet;
	}
	
	public static void mergedSheet(HSSFSheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn ){
		//表示合并B2,B3  
		 sheet.addMergedRegion(new CellRangeAddress(     
			  firstRow, //first row (0-based)  from 行     
			  lastRow, //last row  (0-based)  to 行     
			  firstColumn, //first column (0-based) from 列     
			  lastColumn  //last column  (0-based)  to 列      
	     ));  
	}
	
	public static HSSFCellStyle getRowTitleStyle(HSSFWorkbook workbook) {  
		//设置字体;  
        HSSFFont font = workbook.createFont();  
        //设置字体大小;  
        font.setFontHeightInPoints((short) 10);  
        //设置字体名字;  
        font.setFontName("Arial");  
        //font.setItalic(true);  
        //font.setStrikeout(true);  
        //设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        //设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);  
        //设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        //设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);  
        //设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        //设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);  
        //设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);  
        //在样式用应用设置的字体;  
        style.setFont(font);  
        //设置自动换行;  
        style.setWrapText(false);  
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        return style;  
	}
	
	public static HSSFCellStyle getCellTitleStyle(HSSFWorkbook workbook) {  
		//设置字体;  
        HSSFFont font = workbook.createFont();  
        //设置字体大小;  
        font.setFontHeightInPoints((short) 12);  
        //设置字体名字;  
        font.setFontName("Arial"); 
        //font.setItalic(true);  
        //font.setStrikeout(true);  
        //设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置背景颜色
        style.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
        //设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        //设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);  
        //设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        //设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);  
        //设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        //设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);  
        //设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);  
        //在样式用应用设置的字体;  
        style.setFont(font);  
        //设置自动换行;  
        style.setWrapText(false);  
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        return style;  
	}
	
	public static HSSFCellStyle getRowStyle(HSSFWorkbook workbook) {  
		//设置字体;  
        HSSFFont font = workbook.createFont();  
        //设置字体大小;  
        font.setFontHeightInPoints((short) 10);  
        //设置字体名字;  
        font.setFontName("Arial");  
        //font.setItalic(true);  
        //font.setStrikeout(true);  
        //设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        //设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);  
        //设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        //设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);  
        //设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        //设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);  
        //设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);  
        //在样式用应用设置的字体;  
        style.setFont(font);  
        //设置自动换行;  
        style.setWrapText(false);  
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        return style;  
	}
	
	public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {  
		//设置字体;  
        HSSFFont font = workbook.createFont();  
        //设置字体大小;  
        font.setFontHeightInPoints((short) 10);  
        //设置字体名字;  
        font.setFontName("Arial");  
        //font.setItalic(true);  
        //font.setStrikeout(true);  
        //设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        //设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);  
        //设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        //设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);  
        //设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        //设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);  
        //设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        //设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);  
        //在样式用应用设置的字体;  
        style.setFont(font);  
        //设置自动换行;  
        style.setWrapText(false);  
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        //设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        return style;  
	}
}
