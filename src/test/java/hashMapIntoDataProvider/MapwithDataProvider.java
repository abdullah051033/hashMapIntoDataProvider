package hashMapIntoDataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MapwithDataProvider {
	
	
	@Test(dataProvider = "testdata")
	public void test(Map mapdata) {
		System.out.println("-------Test Started ---------");
		System.out.println(mapdata.get("username"));
		System.out.println(mapdata.get("password"));
		System.out.println(mapdata.get("id")); 
		System.out.println("-------Test Finish ---------");
	}
	
	
	
	
	@DataProvider(name= "testdata")
	public Object[][] dataproviderMethod() throws IOException {
		
		String ExcelFilepath = System.getProperty("user.dir") + "//src//main//resources//testdata//testdata.xlsx";	
		
		File file = new File(ExcelFilepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowcount][1];  //declaring Object array size like " Object[][] = new Object[4][1]"
		
		
		for(int i=0; i<rowcount; i++) {  
			Map<Object, Object> datamap = new HashMap<Object, Object>(); //declaring hashmap to store value from excel sheet//for row iteration
			for(int j=0; j<colcount; j++) {						//for column/cell iteration
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
			
		return obj;	  
	}
	
	
}
