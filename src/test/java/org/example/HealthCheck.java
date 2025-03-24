package org.example;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
public class HealthCheck {
    @SneakyThrows
    public static void main(String[] args) {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+" /test_data/Book1.xlsx");
        // XSSFWorkbook->XSSFSheet->XSSFSheet->Row->Cells
        XSSFWorkbook xssf = new XSSFWorkbook(fis);
        XSSFSheet xssfSheet = xssf.getSheetAt(0);
        System.out.println("the no of row "+xssfSheet.getLastRowNum());
        System.out.println("the no of col "+xssfSheet.getRow(0).getLastCellNum());
        for(int i=0; i<=xssfSheet.getLastRowNum(); i++){
            for(int j=0; j<xssfSheet.getRow(i).getLastCellNum(); j++){
                System.out.print(xssfSheet.getRow(i).getCell(j).getStringCellValue()+" ");
            }
            System.out.println();
        }
    }
}
