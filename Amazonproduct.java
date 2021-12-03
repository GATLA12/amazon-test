Public class Amazonproduct 
{
@BeforeTest
	public void launchBrowser() 
{
		WebDriverManager.chromedriver().setup();
		// Instantiate a ChromeDriver class.
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().window().maximize();  
}
@Test
Public void testcase 01 ()
{
//Search for a product. Verify the list.
driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).click(); 
driver.findElement(By.xpath("//*[@id='productTitle']")).click(); 
   
//Add a product to the cart. Verify that the added product is in the cart.

driver.findElement(By.xpath("//*[@name='submit.add-to-cart']")).click(); 
driver.findElement(By.xpath("//*[@class='a-button-input']")).click();  

 //Increase the number of items in the cart to 10.Verify that the total price changed.
for(int i=0:i<=10;i++)
{
driver.findElement(By.xpath("//*[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).click(); 
}


//Remove a product from the cart. Verify the change.

driver.findElement(By.xpath("//*[@class='nav-cart-text-container']")).click(); 
driver.findElement(By.xpath("//*[@class='submit.delete.C515707c5-1aed-473e-a84f-21e464b752fd']")).click();

//after Remove the product from the cart and Verify the change. 
driver.findElement(By.xpath("//*[@class='nav-cart-text-container']")).click(); 
	}
	
	@AfterTest
	public void teardown()
 {
		driver.close();
	}



