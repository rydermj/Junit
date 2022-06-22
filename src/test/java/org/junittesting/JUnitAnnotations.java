package org.junittesting;

import java.io.IOException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnitAnnotations extends BaseClass {
	
	
	
	@BeforeClass
	public static void declare() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		maxWindow();
	}
	@Test
	public void scl() throws IOException {
		WebElement myLinkText = myLinkText("Help");
		goDown(myLinkText);
		ss("down");
	}
	
	
	@Test
	public void input() {
		WebElement id = myId("email");
		input(id, "test run jUnit");
		
		
	}

	@Test
	public void input1() {
		WebElement pass = myId("pass");
		input(pass, "test password");
	}

	
	@Before
	public void ss1() throws IOException {
		ss("before test");
		Date date = new Date();
		System.out.println(date);
	}
	@After
	public void ss2() throws IOException {
		ss("after test");
		Date date = new Date();
		System.out.println(date);
	}
	
	@AfterClass
	public static void close() {
		quit();
	}
	
	

}
