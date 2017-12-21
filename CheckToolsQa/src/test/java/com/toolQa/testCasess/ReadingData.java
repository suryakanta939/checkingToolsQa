package com.toolQa.testCasess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.toolsQa.commonUtils.ReadDataFromExcel;


public class ReadingData {
//	@Test
//	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException{
//		int data=ReadDataFromExcel.getintData("dataSheet", "Details", 2, 4);
//		System.out.println(data);
//	}
	
  @Test(dataProvider="getdata")
  public void f(String fn,String ln,String country,String month,String day,
			String year,String phone,String email ) throws EncryptedDocumentException, InvalidFormatException, IOException {
//	  String text=ReadDataFromExcel.getData("dataSheet", "Details", 2, 2);
//	  System.out.println(text);
	  if(fn==null||ln==null||country==null||month==null||day==null||year==null||phone==null||email==null){
		  System.out.println("getting the null value");
	  }else{
		  System.out.println(fn);
		  System.out.println(ln);
		  System.out.println(country);
		  System.out.println(month);
		  System.out.println(day);
		  System.out.println(year);
		  System.out.println(phone);
		  System.out.println(email);
	  }
  }
  
@DataProvider
  public Object[][] getdata() throws EncryptedDocumentException, InvalidFormatException, IOException{
	  DataFormatter formatter=new DataFormatter();
	  File f=new File("TestData");
		File fs=new File(f,"dataSheet.xlsx");
		FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("Details");
		int rowNo=sh.getLastRowNum();
		Object[][] obj=new Object[rowNo+1][8];
		for(int i=1;i<rowNo+1;i++){
			Row rw=sh.getRow(i);
			if(rw==null){
				
			}else{
				for(int j=0;j<8;j++){
					String data=formatter.formatCellValue(rw.getCell(j));
					obj[i][j]=data;
			}	
			}
				
		}
		return obj;
  }
}
