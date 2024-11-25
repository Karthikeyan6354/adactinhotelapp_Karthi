package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\adactinHotel_LoginData.xlsx";
		
		ExcelUtilities xlutil=new ExcelUtilities(path);
		int totalrows=xlutil.getrowCount("Sheet1");
		int totalcol=xlutil.getcellCount("Sheet1", 1);
		
		String Logindata[][]=new String[totalrows][totalcol]; //created for 2 dimension array
		
		for (int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcol;j++)
			{
				Logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return Logindata;	//returning two dimension array	
		
	}
	
	@DataProvider(name="BookHotelData")
	public String [][] BookingInfo() throws IOException
	{
		String path=".\\testData\\adactinHotel_BookHotelData.xlsx";
		
		ExcelUtilities xlutil=new ExcelUtilities(path);
		int totalrows=xlutil.getrowCount("Sheet1");
		int totalcol=xlutil.getcellCount("Sheet1", 1);
		
		String Logindata[][]=new String[totalrows][totalcol]; //created for 2 dimension array
		
		for (int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcol;j++)
			{
				Logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return Logindata;	//returning two dimension array	
		
	}
	
	

}
