package org.example.utils;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
@SuppressWarnings("unused")
public class ExcelUtilities {
    private FileInputStream fileInputStream;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;
    private XSSFRow xssfRow;
    private XSSFCell xssfColumn;
    private CellStyle cellStyle;

    private String path;
    ExcelUtilities(String path){
        this.path = path;
    }
    @SneakyThrows
    public int getRowNum(){
        fileInputStream=new FileInputStream(path);
        xssfWorkbook=new XSSFWorkbook(fileInputStream);
        xssfSheet=xssfWorkbook.getSheetAt(0);
        return xssfSheet.getLastRowNum();
    }
    @SneakyThrows
    public int getColNum(){
        fileInputStream=new FileInputStream(path);
        xssfWorkbook=new XSSFWorkbook(fileInputStream);
        xssfSheet=xssfWorkbook.getSheetAt(0);
        return xssfSheet.getRow(0).getLastCellNum();
    }
    @SneakyThrows
    public String getCellData(int rowNum, int colNum){
        fileInputStream=new FileInputStream(path);
        xssfWorkbook=new XSSFWorkbook(fileInputStream);
        xssfSheet=xssfWorkbook.getSheetAt(0);
        xssfRow=xssfSheet.getRow(rowNum);
        xssfColumn=xssfRow.getCell(colNum);
        return xssfColumn.getStringCellValue();
    }

}
