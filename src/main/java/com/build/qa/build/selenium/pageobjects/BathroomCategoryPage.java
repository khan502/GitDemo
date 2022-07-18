package com.build.qa.build.selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import junit.framework.Assert;

public class BathroomCategoryPage {
	WebDriver driver;
	protected Wait<WebDriver> wait;
	private By productBrand;
	
	public BathroomCategoryPage(WebDriver driver,Wait<WebDriver> wait) {
		this.driver=driver;
		productBrand=By.linkText("Single Handle");
	}
	
	public boolean onBathroomCategoryPage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(productBrand)) != null;
	}
	
	public String addBathroomItem() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.linkText("Single Handle")).click();
		Thread.sleep(5000);
		String item=driver.findElement(By.xpath("//a[@title='Pfister Pfirst Series™ Single Handle Monoblock Bathroom Sink Faucet in Polished Chrome']")).getAttribute("title");
		driver.findElement(By.xpath("//a[@title='Pfister Pfirst Series™ Single Handle Monoblock Bathroom Sink Faucet in Polished Chrome']")).click();
		driver.findElement(By.cssSelector("input[value='Add to Cart']")).click();
		Thread.sleep(5000);
		WebElement e=driver.findElement(By.xpath("(//div[@class='cart']/descendant::span[text()='Cart'])[1]"));
		Actions a= new  Actions(driver);
		a.moveToElement(e).click().build().perform();
		return item;
	}

	
	public void verifyCart(String item) {
		int count=0;
		List<WebElement> its=driver.findElements(By.xpath("//div[@class='cl-name']"));
		for(WebElement e:its) {
			String actVal=e.findElement(By.tagName("a")).getAttribute("title");
			if(actVal.equalsIgnoreCase(item)) {
				count=count+1;
			}
		}
		Assert.assertTrue(count>0);
	}
}
