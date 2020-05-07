package com.datadriven.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.excel.utility.Xls_Reader;

public class DataDrivenTest {
	public static void main(String[] args) throws InterruptedException {
		
	System.setProperty("webdriver.chrome.driver","C:\\Users\\jasha\\Documents\\eclipse-workspace\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
driver.get("https://prod.aptence.com/");
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close basecamp-close-btn']")));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement popup = driver.findElement(By.xpath("//button[@class='close basecamp-close-btn']"));
	Thread.sleep(5000);
	js.executeScript("arguments[0].click();", popup);
//	popup.click();
	
	driver.findElement(By.xpath("//button[contains(text(),'LOGIN')]")).click();
	driver.findElement(By.id("login-email")).sendKeys("marketing_manager");
	driver.findElement(By.id("login-password")).sendKeys("qwerty1234");
	driver.findElement(By.id("submit_login_from")).click();
	driver.findElement(By.xpath("//a[@href='/marketing_manager/send_mobile_sms/']")).click();
	Thread.sleep(2000);
	
//	Select msgmethod = new Select(driver.findElement(By.id("id_sms_method")));
//	msgmethod.selectByValue("1");
	
	driver.findElement(By.id("id_send_messages_to_single_user")).click();
	
	
	Xls_Reader reader = new Xls_Reader("C:\\Users\\jasha\\Documents\\eclipse-workspace\\AptenceMessages\\src\\com\\testdata\\Aptence Messages.xlsm");
	int totalrows = reader.getRowCount("Sheet1");
	reader.addColumn("Sheet1", "Status");
	for(int i=2;i<=totalrows;i++) {
		
	String s =reader.getCellData("Sheet1", "mobile", i);
	String mobile = s.replaceAll("^.|.$","");
	String name = reader.getCellData("Sheet1", "name", i);
	String username = reader.getCellData("Sheet1", "username", i);
	String password = reader.getCellData("Sheet1", "password", i);
	
	String message1 = "Dear "+name+", ";
	String message2	= "Thanks for joining Aptence, your login credentials are:";
	String message3 = "Username: "+username+" ";
	String message4 = "Password: "+password+" ";
	String message5 = "App Download Link: aptence.app.link ";
	String message6 = "Web: www.aptence.com";
	String message7 = "Thanks, ";
	String message8 = "Team Aptence ";
	String keysPressed =  Keys.chord(Keys.SHIFT, Keys.RETURN);
	
	driver.findElement(By.id("id_mobile_number")).clear();
	Thread.sleep(1000);
	driver.findElement(By.id("id_mobile_number")).sendKeys(mobile);
	
	driver.findElement(By.id("id_message")).clear();
	Thread.sleep(1000);
	//driver.findElement(By.id("id_message")).sendKeys("Success!");
	WebElement message = driver.findElement(By.id("id_message"));
	message.sendKeys(message1);
	message.sendKeys(keysPressed);
	message.sendKeys(message2);
	message.sendKeys(keysPressed);
	message.sendKeys(message3);
	message.sendKeys(keysPressed);
	message.sendKeys(message4);
	message.sendKeys(keysPressed);
	message.sendKeys(message5);
	message.sendKeys(keysPressed);
	message.sendKeys(message6);
	message.sendKeys(keysPressed);
	message.sendKeys(message7);
	message.sendKeys(keysPressed);
	message.sendKeys(message8);
	message.sendKeys(keysPressed);
	
	driver.findElement(By.xpath("//input[@class='btn btn-success form-control']")).click();
	System.out.println(message1);
	reader.setCellData("Sheet1", "Status", i, "Pass");
	Thread.sleep(2000);
	driver.switchTo().alert().accept();
	Thread.sleep(2000);
	}
	}
}
