package com.build.qa.build.selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import junit.framework.Assert;

public class CartPage {
	WebDriver driver;
	protected Wait<WebDriver> wait;
	private By productBrand;
	
	public CartPage(WebDriver driver,Wait<WebDriver> wait) {
		this.driver=driver;
		productBrand=By.xpath("//h1[text()='SHOPPING CART']");
	}
	
	public boolean onCartPage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(productBrand)) != null;
	}
	
	public void verifyProductBrand(String etBrand) throws InterruptedException {
		Thread.sleep(5000);
		String aBrand=driver.findElement(By.cssSelector("h2[class='product__brand']")).getText();
		Assert.assertEquals("The Brand is not equal",etBrand.trim(), aBrand.trim());
	}

	
	public void verifyProductID(String etProdId) {
		String aProdId=driver.findElement(By.cssSelector("span[itemprop='productID']")).getText();
		Assert.assertTrue(aProdId.contains(etProdId));
	}
	
	public void clickOnCart() {
		WebElement e=driver.findElement(By.xpath("(//div[@class='cart']/descendant::span[text()='Cart'])[1]"));
		Actions a= new  Actions(driver);
		a.moveToElement(e).click().build().perform();
	}
	
	
	public void removeFromcart() {
		List<WebElement> its=driver.findElements(By.xpath("//div[@class='cl-name']"));
		if(its.size()>0) {
			for(int i=0;i<its.size()-1;i++) {
			driver.findElement(By.xpath(("//a[text()='Remove'])[1]"))).click();
			driver.findElement(By.linkText("Yes, Remove")).click();
			}
		}
	}
	
	public void icreaseQuantity() {
		List<WebElement> its=driver.findElements(By.xpath("//div[@class='cl-name']"));
		if(its.size()>0) {
			for(int i=0;i<its.size()-1;i++) {
			driver.findElement(By.xpath(("(//input[@type='text' and @name='updateQuantity'])[i+1]"))).sendKeys("2");
			}
		}
	}
	
	public void verifyQuantity(String id) {
		String dynamiclocator= "(//input[@type='text' and @name='updateQuantity' and @data-sku-id='"+id+"'])"; 
		String actVal=driver.findElement(By.xpath(dynamiclocator)).getAttribute("value");
		Assert.assertEquals("2", actVal);
		
	}

}
