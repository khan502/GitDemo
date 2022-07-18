package com.build.qa.build.selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import junit.framework.Assert;

public class ProductPage {
	WebDriver driver;
	protected Wait<WebDriver> wait;
	private By productBrand;
	
	public ProductPage(WebDriver driver,Wait<WebDriver> wait) {
		this.driver=driver;
		productBrand=By.cssSelector("h2[class='product__brand']");
	}
	
	public boolean onProductPage() {
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
	
	public CartPage clickOnCart() {
		WebElement e=driver.findElement(By.xpath("(//div[@class='cart']/descendant::span[text()='Cart'])[1]"));
		Actions a= new  Actions(driver);
		a.moveToElement(e).click().build().perform();
		return new CartPage(driver, wait);
	}
	
		
	public void searchForProduct(String value) {
		driver.findElement(By.xpath("//input[contains(@class,'text-input search react-search-input-normal')]")).sendKeys(value);
		driver.findElement(By.xpath("//div[@id='react-type-ahead-normal']/descendant::a[@class='fg-icon-search']")).click();
	}
	
	public void clickOnAddCart() {
		driver.findElement(By.cssSelector("input[value='Add to Cart']")).click();
	}
	
	
}
