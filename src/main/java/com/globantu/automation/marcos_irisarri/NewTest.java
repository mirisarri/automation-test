package com.globantu.automation.marcos_irisarri;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NewTest {

	@Test
	public void f() {

		WebDriver driver;
		// Mala practica: Referencia al binario del driver no parametrizada.
		System.setProperty("webdriver.chrome.driver", "/Users/marcos.irisarri/Documents/Automation/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Empieza el test
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("jajaja");
		driver.findElement(By.name("q")).submit();
		String title = driver.getTitle();
		System.out.println("Titulo de la pagina: " + title);
		Assert.assertEquals(title, "Google");
	}
}
