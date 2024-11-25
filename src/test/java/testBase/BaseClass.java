package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public Logger logger;
	
	@BeforeClass (groups= {"Sanity","Regression","Master","DataDriven","Functional"})
 	@Parameters ({"os","browser"})
	public void setup(String os, String br) throws IOException	
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);
				
		switch(br.toLowerCase()){
		
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default: System.out.println("Invalid Browser"); return;
			
		}
		
		logger=LogManager.getLogger(this.getClass());
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appUrl"));
			
	}
	
	@AfterClass (groups= {"Sanity","Regression","Master","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String getRandomAlphabetic(int value)
	{
		String alpha=RandomStringUtils.randomAlphabetic(value);
		return alpha;
	}   
	
	public String getRandomNumeric(int value)
	{
		String numeric=RandomStringUtils.randomNumeric(value);
		return numeric;
	}
	
	public String getalphanumberic(int value)
	{
		String alphanumer=RandomStringUtils.randomAlphanumeric(value);
		return alphanumer;		
	}
	
	public String CaptureScreen(String tname) {
		
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot) driver; 
		File Sourcefile=ts.getScreenshotAs(OutputType.FILE);
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
		File targetfile=new File (targetfilepath);
		
		Sourcefile.renameTo(targetfile);
		return targetfilepath;
	}
	
	
	

}
