package com.toolQa.testCasess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CompreTwoExcelFiles {
	String data;
	String data1;

	@Test()
	public void comaringexcel() throws EncryptedDocumentException, InvalidFormatException, IOException{
		
		DataFormatter formatter=new DataFormatter();
		  File f=new File("TestData");
		  File fs=new File(f,"dataSheet.xlsx");
		  FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
		  FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\datacheck.xlsx");
		  Workbook wb=WorkbookFactory.create(fis);
		  Sheet sh=wb.getSheet("Details");
		  Workbook wb1=WorkbookFactory.create(fis1);
		  Sheet sh1=wb1.getSheet("details");
		  for(int i=1;i<7;i++){
			  Row rw=sh.getRow(i);
			  Row rw1=sh1.getRow(i);
			  if(rw==null && rw1==null ){  
			  }else{
				  for(int j=0;j<8;j++){
					  data=formatter.formatCellValue(rw.getCell(j));
					  data1=formatter.formatCellValue(rw1.getCell(j));
					  if(!data.equals(data1))
					 System.out.println(data1+" in row "+i +" and column "+j +" is not matching");
					 }
					
				  }
		  }
	}
}