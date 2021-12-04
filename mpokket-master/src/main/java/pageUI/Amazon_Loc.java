package pageUI;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import basePage.BasePage;

public class Amazon_Loc extends BasePage{
	
	public static final Logger log= Logger.getLogger(Amazon_Loc.class.getName());
    
	@FindBy(id="twotabsearchtextbox") WebElement txtSearch;
	@FindBy(id="nav-search-submit-button") WebElement btnSearch;
	@FindBy(xpath="//span[text()='Department']") WebElement txtDepartment;
	@FindBy(xpath="(//div[@class='a-section aok-relative s-image-fixed-height'])[1]")WebElement linkProduct;
	@FindBy(xpath="//img[@class='s-image']") List<WebElement> linkProducts;
	@FindBy(id="add-to-cart-button") WebElement btnAddToCart;
	//@FindBy(class="a-column a-span12 a-text-left") WebElement area;
	@FindBy(id="hlb-view-cart-announce") WebElement btnCart;
	@FindBy(xpath="//span[@class='a-truncate-cut']") WebElement txtProductName;
	@FindBy(id="a-autoid-0-announce") WebElement dropdownQuantity;
	@FindBy(id="quantity_10") WebElement dropdownvalue;
	@FindBy(name="quantityBox") WebElement txtQuantity;
	@FindBy(id="a-autoid-1-announce") WebElement btnUpdate;
	@FindBy(xpath="//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']") WebElement txtOneProductAmount;;
	@FindBy(xpath="(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap'])[1]") WebElement txtTenProductsAmount;
	@FindBy(xpath="(//span[@class='a-declaractive'])[2]") WebElement btnDelete;
	@FindBy(xpath="(//span[@class='a-size-base'])[1]") WebElement txtSuccessMessage;
	
	public Amazon_Loc(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	public void amazon() throws Exception {
		txtSearch.clear();
		Thread.sleep(1000);
		txtSearch.sendKeys(c.getData("product"));
		btnSearch.click();
		elementVisible(100, txtDepartment);
		for (WebElement productName : linkProducts) {
			if(productName.getAttribute("alt").equalsIgnoreCase(c.getData("product"))) {
			productName.click();
			break;
			}
		}
		
		elementVisible(100, btnAddToCart);
		btnAddToCart.click();
		elementVisible(100, btnCart);
		btnCart.click();
		elementVisible(100, txtProductName);
		String expected =txtProductName.getText();
		String actual = c.getData("product");
		Assert.assertEquals(actual, expected); 
		dropdownQuantity.click();
		elementVisible(100, dropdownvalue);
		Thread.sleep(1000);
		dropdownvalue.click();
		elementVisible(100, txtQuantity);
		txtQuantity.sendKeys(c.getData("productQuantity"));
		btnUpdate.click();
		
		
		String a =txtOneProductAmount.getText();
		String str = a.substring(1);
		double oneItem_Amount = Double.parseDouble(str);
		
		String b = txtTenProductsAmount.getText();
		String str1 = b.substring(1);
		double TenItems_Amount = Double.parseDouble(str1);
		
		
		if(oneItem_Amount<TenItems_Amount)  {
			System.out.println("Amount has updated based on product quantity");
		}
		
		Thread.sleep(2000);
		btnDelete.click();
		elementVisible(100,txtSuccessMessage);
		String result = txtSuccessMessage.getText();
		System.out.println(result);
		
	}

	}

	 

