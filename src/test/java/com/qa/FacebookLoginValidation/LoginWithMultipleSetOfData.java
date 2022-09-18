package com.qa.FacebookLoginValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginWithMultipleSetOfData {
	
	@Test(dataProvider = "Credentials")
	public void verifyLoginCredentials(String scenario, String username, String password) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/login");
		
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value = 'Log in']")).click();
				
		if(scenario.equals("BothWrong")) {
			String errorMessage = driver.findElement(By.cssSelector(".message-error ul >li")).getText();
			Assert.assertEquals(errorMessage, "No customer account found");
		}
		else if(scenario.equals("correctusername")) {
			String errorMessage = driver.findElement(By.cssSelector(".message-error ul >li")).getText();
			Assert.assertEquals(errorMessage, "The credentials provided are incorrect");
		}
		else if(scenario.equals("correctpassword")) {
			String errorMessage = driver.findElement(By.cssSelector(".message-error ul >li")).getText();
			Assert.assertEquals(errorMessage, "No customer account found");
		}
		
		driver.quit();
	}
	
	@DataProvider(name = "Credentials")
	public Object[][] getData(){
		return new Object[][] {
			{"BothWrong", "sonukumar@gmail.com", "sonu123"},
			{"correctusername", "sonukumar1239@gmail.com", "sonu123"},
			{"correctpassword", "sonukumar@gmail.com", "sonu@1239"}
		};
	}
}
