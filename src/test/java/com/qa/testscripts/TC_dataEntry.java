package com.qa.testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_dataEntry extends TestBase {

	@Test(enabled= true)
	public void enterData()
	{
		js= (JavascriptExecutor) driver;
		while(!driver.findElement(By.id("datepicker")).isDisplayed())
		{
			js.executeScript("window.scrollBy(0,100)");
		}
		
		//Adding the Firstname and Validating whether name entered correctly
		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("firstname"));
		Assert.assertEquals(driver.findElement(By.name("firstname")).getAttribute("value"),prop.getProperty("firstname"));
		
		//Adding the Lastname and Validating whether name entered correctly
		driver.findElement(By.name("lastname")).sendKeys(prop.getProperty("lastname"));
		Assert.assertEquals(driver.findElement(By.name("lastname")).getAttribute("value"),prop.getProperty("lastname"));
		
		//Selecting Gender and Validating
		String gender= prop.getProperty("gender");
		if(gender.contains("Male"))
		{
			driver.findElement(By.id("sex-0")).click();
			Assert.assertTrue(driver.findElement(By.id("sex-0")).isSelected());
		}
		else
		{
			driver.findElement(By.id("sex-1")).click();
			Assert.assertTrue(driver.findElement(By.id("sex-1")).isSelected());
		}
		
		//Selecting and Validating YOE
		int yop= Integer.parseInt(prop.getProperty("yop"))-1;
		driver.findElement(By.id("exp-"+yop)).click();
		Assert.assertTrue(driver.findElement(By.id("exp-"+yop)).isSelected());
		
		//Selecting and Validating Dates
		driver.findElement(By.id("datepicker")).sendKeys(prop.getProperty("date"));
		Assert.assertEquals(driver.findElement(By.id("datepicker")).getAttribute("value"),prop.getProperty("date"));
		
		//Selecting and Validating Profession
		if((prop.getProperty("profession")).contains("Automation"))
		{
			driver.findElement(By.id("profession-1")).click();
			Assert.assertTrue(driver.findElement(By.id("profession-1")).isSelected());
		}
		else
		{
			driver.findElement(By.id("profession-0")).click();
			Assert.assertTrue(driver.findElement(By.id("profession-0")).isSelected());
		}
		driver.findElement(By.id("tool-2")).click();
		
		//Selecting and Validating Continent
		driver.findElement(By.className("input-xlarge")).click();
		WebElement dropdown= driver.findElement(By.id("continents"));
		Select continent= new Select(dropdown);
		continent.selectByVisibleText(prop.getProperty("continent"));
		Assert.assertEquals(driver.findElement(By.className("input-xlarge")).getAttribute("value"),prop.getProperty("continent"));
		
		while(!driver.findElement(By.id("submit")).isDisplayed())
		{
			js.executeScript("window.scrollBy(0,100)");
		}
		
		Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
		driver.findElement(By.id("submit")).click();
	}
}
