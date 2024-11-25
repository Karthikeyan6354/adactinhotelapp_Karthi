package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileInputStream fis;
	public FileOutputStream fos;
	String path;
	
	public ExcelUtilities(String path) {
		this.path=path;
	}
	
	public int getrowCount(String Sheetname) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(Sheetname);
		int totalrows=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return totalrows;		
	}
	
	public int getcellCount(String Sheetname, int rowno) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(Sheetname);
		row=sheet.getRow(rowno);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
		
	}
	
	public String getCellData(String Sheetname, int rowno, int Cellno) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(Sheetname);
		row=sheet.getRow(rowno);
		cell=row.getCell(Cellno);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try
		{
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		if (!xlfile.exists())   //If file not exists then create new file
		{
			workbook=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			workbook.write(fos);
		}
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(path);
		
		if(workbook.getSheetIndex(sheetname)==-1)  //If sheet not exists then create new sheet
			workbook.createSheet(sheetname);
		sheet=workbook.getSheet(sheetname);

        if (sheet.getRow(rownum)==null)  //If row not exits, then create new row
             	sheet.createRow(rownum);
        row=sheet.getRow(rownum);
        
        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fos=new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
        
        
	}
	
	
	
	
	
	
	
	
	

}
