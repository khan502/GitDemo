package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;
import com.build.qa.build.selenium.pageobjects.CartPage;
import com.build.qa.build.selenium.pageobjects.ProductPage;

import junit.framework.Assert;

public class HomePage extends BasePage {
	
	private By homePageWrapper;
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");
	}
	
	public boolean onHomePage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
	}
	
	public ProductPage searchForProduct(String value) {
		driver.findElement(By.xpath("//input[contains(@class,'text-input search react-search-input-normal')]")).sendKeys(value);
		driver.findElement(By.xpath("//div[@id='react-type-ahead-normal']/descendant::a[@class='fg-icon-search']")).click();
		return new ProductPage(driver, wait);
	}
	
	public CartPage clickOnCart() {
		WebElement e=driver.findElement(By.xpath("(//div[@class='cart']/descendant::span[text()='Cart'])[1]"));
		Actions a= new  Actions(driver);
		a.moveToElement(e).click().build().perform();
		return new CartPage(driver, wait);
	}
	
	public void clickOnAllProducts() {
		driver.findElement(By.linkText("All Products")).click();
	}
	
	//Bathroom Plumbing
	public void searchAndSelectCategory(String category) {
		driver.findElement(By.xpath("//div[@data-dname='category']/descendant::input[@placeholder='Search...']")).sendKeys(category,Keys.ENTER);
	}
	
	public void clickOnCategory(String catgry) {
		String dynamicLocator="//p[text()='"+catgry+"']";
		driver.findElement(By.xpath(dynamicLocator)).click();
	}
	
	public void searchAndSelectBrand(String category) {
		driver.findElement(By.xpath("//div[@data-dname='brand']/descendant::input[@placeholder='Search...']")).sendKeys(category,Keys.ENTER);
		driver.findElement(By.xpath("//li[contains(@data-category-url,'/category/bathroom-plumbing/bathroom-faucets/brizo')]/label/label")).click();
	}
	
	public void verifyResults(int num) {
		String actVal=driver.findElement(By.xpath("//div[@class='word total-record']")).getAttribute("data-total-record");
		Assert.assertEquals(num, Integer.parseInt(actVal));
	}
}
