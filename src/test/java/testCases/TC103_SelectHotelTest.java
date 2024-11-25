package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;

public class TC103_SelectHotelTest extends BaseClass{

	@BeforeMethod
	public void info_searchHotel() 
	{
		logger.info("*********Started TC103_SelectHotelTest********");
		
		logger.info("Landed in log-in page.....");
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();
		logger.info("Provided user name and password");
		
		logger.info("landed in search hotel page....");
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("London");
		shp.select_Hotels("Hotel Creek");
		shp.select_roomType("Double");
		shp.select_no_of_rooms(2);
		shp.set_checkInDate("10/12/2025");
		shp.set_checkOutDate("12/12/2025");
		logger.info("Provided necessary information for search hotel page....");
		shp.click_Search();
		logger.info("cliccked search button......");
	}
	
	@AfterMethod
	public void logout() 
	{ 
		LoginPage lp=new LoginPage(driver);
		logger.info("clicked logout and login again..");
		lp.click_logout();
		lp.click_loginAgain();
	}
	
	@Test (priority=1, groups="Regression")
	public void valid_selectHotel() 
	{
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radionButton();	
		selctHotPage.click_Continue();
		logger.info("landed in select hotel page and verifying the url..");
		Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel.php"));
		System.out.println("Booking page is verified. . ");	
	}
	
	@Test (priority=2, groups="Regression")
	public void validate_searchhotel() 
	{
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radionButton();
		logger.info("landed in select hotel page and verifying the location..");
		System.out.println("Location of hotel is: " +selctHotPage.get_Location());
		Assert.assertEquals("London", selctHotPage.get_Location());
		System.out.println("Location validated");
		
		Assert.assertEquals("Hotel Creek", selctHotPage.get_hotelName());
		System.out.println("Hotel Name verified");	
				
	}
	
	@Test (priority=3, groups="Regression")
	public void withoutSelect_hotel() 
	{		
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_Continue();		
		logger.info("landed in select hotel page and clicked continue button..");
	}
	
	@Test (priority=4, groups="Regression")
	public void priceCalculation() 
	{
		logger.info("landed in select hotel page and validating each field..");
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		double price_pernight=selctHotPage.get_pricePerNight();
		double totalprice=selctHotPage.get_totalPrice();
		int noOfRooms=selctHotPage.get_noOfRooms();
		int noOfDays=selctHotPage.get_noOfDays();
		double expectedPrice=price_pernight*noOfRooms*noOfDays;
		System.out.println("Total price: "+totalprice);
		System.out.println("No of Rooms: "+noOfRooms);
		System.out.println("No of Days: " + noOfDays);
		Assert.assertEquals(expectedPrice, totalprice);
		System.out.println("Amount Validated");
				
	}


	
}
