package listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import basePage.BasePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Listener extends BasePage implements ITestListener{
	
	public void onTestStart(ITestResult arg0) {
		Reporter.log("Test start Running:"+arg0.getMethod().getMethodName());
	}
    public void onTestskipped(ITestResult arg0) {
    	Reporter.log("Test is skipped:"+arg0.getMethod().getMethodName());
    }
    public void onTestSuccess(ITestResult arg0) {
    	Reporter.log("Test is success:"+arg0.getMethod().getMethodName());
    	Calendar cal=Calendar.getInstance();
        SimpleDateFormat simpleDate =new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
    	
        String methodName = arg0.getName();
       
        if(arg0.isSuccess())  {
        	Screenshot screenshot =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        	String destination = String.format("%s%s%s.png", System.getProperty("user.dir")+"/target/surfire-reports/PassedScreenshots/", methodName, simpleDate.format(cal.getTime()));
           File targetPath=new File(destination);
        	try {
        		ImageIO.write(screenshot.getImage(),"PNG", targetPath);
        		
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	}
        	test.log(LogStatus.PASS, "<a href=' "+targetPath.getAbsolutePath()+"'>"
        			+"<img src=' "+targetPath.getAbsolutePath()+" ' heigh='100' width='100'/></a> ");
        }
    }
    


    public void onTestFailure(ITestResult arg0) {
	Reporter.log("Test is Failed:"+arg0.getMethod().getMethodName());
	Calendar cal=Calendar.getInstance();
	SimpleDateFormat simpleDate =new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
	 String methodName = arg0.getName();
	 
	if(!arg0.isSuccess()) {
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		String destination =String.format("%s%s%s.png", System.getProperty("user.dir")+"/target/surefire-reports/FailedScreenshots/", methodName, simpleDate.format(cal.getTime()));
		File targetPath = new File(destination);
		try {
			ImageIO.write(screenshot.getImage(),"PNG", targetPath);
		}
		catch  (IOException e)  { 
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, "<a href=' "+targetPath.getAbsolutePath()+" '>"
				+"<img src='"+targetPath.getAbsolutePath()+" ' height='100' width='100'/></a> ");					
		}
	
}
    
   public void onFinish(ITestContext arg0) {
	   
   }
   
   public void onStart(ITestContext arg0)  {
	   
   }
   
   public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	   
   }
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
}
}
