package basePage;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	
	public static  WebDriver driver; 
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public static Config c;
	public String log4jpath="log4j.properties";
	
	public void browserLaunch(String browser,String url)  {
		if(browser.equalsIgnoreCase("chrome"))  {
			/*System.setProperty("webdriver.chrome.driver", System.getPropert("user.dir")+"//drivers//chromedriver.exe");
			 * driver=new ChromeDriver(); */
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		PropertyConfigurator.configure(log4jpath);
	}
	
	public void selectOption(WebElement element,int option)   {
		Select s =new Select(element);
		s.selectByIndex(option);
	}
	public void elementVisible(int time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	static {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
	
		extent= new ExtentReports(System.getProperty("user.dir")+"/target/surefire-reports/ExtentReport "+ simpleDate.format(cal.getTime())+" html",false);
	}
	
	@BeforeMethod
	public void startReport(Method result) {
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"  : Test is started");
	}
	@AfterMethod
	public void endReport(ITestResult result) throws Exception {
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName()+" : Test is passed");
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName()+" : Test is failed and the reason is:"+result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName()+" : Test is skipped and the reason is:"+result.getThrowable());
		}
		extent.endTest(test);
		
	}
	
}
