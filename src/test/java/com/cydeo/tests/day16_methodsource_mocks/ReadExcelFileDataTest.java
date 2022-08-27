package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ReadExcelFileDataTest {
    @Test
    public void readBookItUsersTest(){

        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath, "QA3");
        System.out.println("columns = " + excelUtil.getColumnsNames());

        int rowCount = excelUtil.rowCount();

        System.out.println("rowCount = " + rowCount);

        System.out.println(excelUtil.getCellData(5, 0));

        List<Map<String, String>> data = excelUtil.getDataList();
        System.out.print(data);
    }

}
