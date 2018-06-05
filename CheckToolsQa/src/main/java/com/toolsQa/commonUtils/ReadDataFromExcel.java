package com.toolsQa.commonUtils;

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

public class ReadDataFromExcel {

	
	public static String getStringData(String excelsheetName,String sheetName,int rowNo,int columnNo) throws EncryptedDocumentException, InvalidFormatException, IOException{
		File f=new File("TestData");
		File fs=new File(f,excelsheetName+".xlsx");
		System.out.println(fs.getAbsolutePath());
		
		FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
		 Workbook wb=WorkbookFactory.create(fis);
		 Sheet sh=wb.getSheet(sheetName);
		 Row rw=sh.getRow(rowNo);
	    String data=rw.getCell(columnNo).getStringCellValue();
		 return data;
	}
	public static int getintData(String excelsheetName,String sheetName,int rowNo,int columnNo) throws EncryptedDocumentException, InvalidFormatException, IOException{
		File f=new File("TestData");
		File fs=new File(f,excelsheetName+".xlsx");
		System.out.println(fs.getAbsolutePath());
		
		FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
		 Workbook wb=WorkbookFactory.create(fis);
		 Sheet sh=wb.getSheet(sheetName);
		 Row rw=sh.getRow(rowNo);
		 int data=(int) rw.getCell(columnNo).getNumericCellValue();
		 return data;
	}
	
	public static Object[][] readData() throws EncryptedDocumentException, InvalidFormatException, IOException{
		  DataFormatter formatter=new DataFormatter();
		  File f=new File("TestData");
			File fs=new File(f,"dataSheet.xlsx");
			FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet("Details");
			int rowNo=sh.getLastRowNum();
			Object[][] obj=new Object[7][8];
			for(int i=1;i<8;i++){
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

