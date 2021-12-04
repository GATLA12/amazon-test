package com.ecommerce.Amazon;


import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basePage.BasePage;
import config.Config;
import pageUI.Amazon_Loc;

public class TC001_Amazon extends BasePage{
public static final Logger log=Logger.getLogger(TC001_Amazon.class.getName());
@Test 
public void amazon() throws Exception{
log.info("****Strating TC001_Amazon****"); 
Amazon_Loc a=new Amazon_Loc(driver);
a.amazon();
log.info("****End of TC001_Amazon****");
}
@BeforeClass
public void beforeTest() throws Exception{
c= new Config(System.getProperty("user.dir")+"//PropertyFiles//TC001_Amazon.properties");
browserLaunch(c.getData("browser"),c.getData("url"));
}
@AfterClass
public void afterTest() {
driver.quit();
extent.endTest(test);
extent.flush();
}
}