package com.build.qa.build.selenium.tests;

import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.BathroomCategoryPage;
import com.build.qa.build.selenium.pageobjects.CartPage;
import com.build.qa.build.selenium.pageobjects.ProductPage;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

public class FergTest extends BaseFramework {

	HomePage homePage;
	ProductPage productPage;
	CartPage cartPage;
	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		 homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @throws InterruptedException 
	 * @assert: That the product page we land on is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	//@Test
	public void searchForProductLandsOnCorrectProduct() throws InterruptedException {
		// TODO: Implement this tes
		navigateToHomePage();
		productPage=homePage.searchForProduct("Moen m6702bn");
		productPage.verifyProductBrand("Moen");
		productPage.verifyProductID("M6702BN");
	
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @throws InterruptedException 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException {
		// TODO: Implement this test
		driver.get(getConfiguration("BATHROOMCATEGORY"));
		BathroomCategoryPage bathroomPage=new BathroomCategoryPage(driver,wait);
		String item=bathroomPage.addBathroomItem();
		bathroomPage.verifyCart(item);
	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @throws InterruptedException 
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() throws InterruptedException {
		// TODO: Implement this test
		navigateToHomePage();
		cartPage=homePage.clickOnCart();
		cartPage.removeFromcart();
		productPage=homePage.searchForProduct("Moen m6702bn");
		productPage.verifyProductBrand("Moen");
		productPage.clickOnAddCart();
		productPage.searchForProduct("pfx146324");
		productPage.verifyProductBrand("Moen");
		productPage.clickOnAddCart();
		cartPage=productPage.clickOnCart();
		cartPage.icreaseQuantity();
		//7806811
		cartPage.verifyQuantity("7806811");
		//2405309
		cartPage.verifyQuantity("2405309");	
		
	}

	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		// TODO: Implement this test
		navigateToHomePage();
		homePage.clickOnAllProducts();
		homePage.searchForProduct("Bathroom Plumbing");
		homePage.clickOnCategory("Bathroom Plumbing");
		homePage.clickOnCategory("Bathroom Faucets");
		homePage.searchAndSelectBrand("Brizo");
		homePage.verifyResults(472);
	}
}
