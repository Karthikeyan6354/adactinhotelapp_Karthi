package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {
	
	public ExtentSparkReporter SparkReporter; //UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; //create test case entries in the report and update the status of test methods
	
	String repname;
	
	public void onStart(ITestContext context) {
		
		String timestmap= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname="Test-Report-"+timestmap+".html";
				
		SparkReporter=new ExtentSparkReporter (".\\reports\\"+repname);
		
		
		SparkReporter.config().setDocumentTitle("Automation Testing");
		SparkReporter.config().setReportName("Functional Testing");
		SparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(SparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		
		System.out.println("Extent report has been generated");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List <String> groups=context.getCurrentXmlTest().getIncludedGroups();
		if(!groups.isEmpty())
			 extent.setSystemInfo("Groups", groups.toString());
	
	}
	
	public void onTestSuccess (ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "The test case Passed is: "+ result.getName());		
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "The test Case Failed is: " + result.getName());
		test.log(Status.INFO, "The test case Failed Cause is: "+ result.getThrowable().getMessage());
		
		try {
			String imgpath=new BaseClass().CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped (ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "The test case Skipped is: "+ result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String Pathofextentreport= System.getProperty("user.dir")+"\\reports\\"+ repname;
		File extentreport=new File(Pathofextentreport);
		
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
