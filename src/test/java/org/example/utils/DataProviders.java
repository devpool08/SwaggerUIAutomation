package org.example.utils;

import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
//TODO: Method Overloading impl and 2D data provider creation
public class DataProviders {
    @SneakyThrows
    @DataProvider(name = "LoginData")
    public Object[][] provideData() {
        String path = System.getProperty("user.dir")+"/test_data/LogInData.xlsx";
        ExcelUtilities excelUtilities = new ExcelUtilities(path);
        int row= excelUtilities.getRowNum();
        int col= excelUtilities.getColNum();
        String[][] data= new String[row][col];
        for(int i=1; i<row; i++){
            for (int j=0;j<col;j++){
                data[i-1][j]= excelUtilities.getCellData(i,j);
                System.out.println(excelUtilities.getCellData(i,j));
            }
        }
        return data;
    }

    public static void main(String[] args) {
        DataProviders dataProviders = new DataProviders();
        dataProviders.provideData();
    }
}
