package com.miro.core;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.Iterator;

public class TestCasesData {

    ExcelManager em;

    public TestCasesData() throws IOException, InvalidFormatException {
        em = new ExcelManager(System.getProperty("user.dir") + "\\src\\test\\java\\com\\miro\\data\\miro_data.xlsx");
    }

    /**
     * Method for retrieving tests data from Excel file
     *
     * @param testName used to specify the sheet to take the data from
     *
     * @author Matías Cárdenas
     */
    public Object[][] getTestData(String testName) throws IOException, InvalidFormatException {
        Sheet testSheet = em.getExcelSheet(testName);

        Iterator<Row> rows = testSheet.iterator();
        Row firstRow = rows.next();
        Object[][] testData = new Object[testSheet.getPhysicalNumberOfRows()-1][firstRow.getPhysicalNumberOfCells()];

        int i = 0;
        while(rows.hasNext()) {
            Row row = rows.next();

            Iterator<Cell> cells = row.iterator();
            int cellNumber = 0;
            while (cells.hasNext()) {
                Cell currentCell = cells.next();
                testData[i][cellNumber] = currentCell.getStringCellValue();
                cellNumber++;
            }
            i++;
        }

        return testData;
    }
}
