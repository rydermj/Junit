package org.junittesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Workbook workbook;
	public static String value;
	public static Sheet sheet;
	public static Cell cell;

	public static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void launchUrl(String siteUrl) {
		driver.get(siteUrl);
	}

	public static void maxWindow() {
		driver.manage().window().maximize();
	}

	public static void ss(String fileNameHere) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshots/" + fileNameHere + ".png");
		FileUtils.copyFile(source, destination);
	}

	public static void input(WebElement WebElementRefName, String yourInput) {
		WebElementRefName.clear();
		WebElementRefName.sendKeys(yourInput);
	}

	public static void btnclick(WebElement element) {
		element.click();
	}

	public static WebElement myXpath(String path) {
		return driver.findElement(By.xpath(path));

	}

	public static WebElement myId(String id) {
		return driver.findElement(By.id(id));

	}

	public static WebElement myCss(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector));

	}

	public static WebElement myClassName(String className) {
		return driver.findElement(By.className(className));

	}

	public static WebElement myLinkText(String linkText) {
		return driver.findElement(By.linkText(linkText));

	}

	public static WebElement myTagName(String tagName) {
		return driver.findElement(By.tagName(tagName));

	}

	public static String getCurrentUrl() {
		return driver.getCurrentUrl();

	}

	public static String getTitle() {
		return driver.getTitle();

	}

	public static void close() {
		driver.close();
	}

	public static void quit() {
		driver.quit();

	}

	public static void impWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void navigateBackword() {
		driver.navigate().back();
	}

	public static void refresh() {
		driver.navigate().refresh();

	}

	public static void alertAccept() {
		impWait();
		driver.switchTo().alert().accept();
	}

	public static void alertcancel() {
		impWait();
		driver.switchTo().alert().dismiss();
	}

	public static void alertinput(String input) {
		impWait();
		driver.switchTo().alert().sendKeys(input);
	}

	public static String windowHandle() {
		return driver.getWindowHandle();
	}

	public static Set<String> windowHandles() {
		return driver.getWindowHandles();
	}

	public static void goDown(WebElement elementName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", elementName);
	}

	public static void goUp(WebElement elementName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", elementName);
	}

	public static void myExcel(String path) throws IOException {
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		  workbook= new XSSFWorkbook(fileInputStream);

	}

	public static Cell myExcelgetCell(String path,String sheetName, int enterRow, int enterCell) throws IOException {
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		 Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(enterRow);
		 return cell= row.getCell(enterCell);

	}
	
	public static void myExcelGetAllData(String sheetName) {
		 sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row eachRows = sheet.getRow(i);
			for (int j = 0; j < eachRows.getPhysicalNumberOfCells(); j++) {
				Cell cell = eachRows.getCell(j);
				int type = cell.getCellType();
				if (type == 1) {
					String value = cell.getStringCellValue();

					System.out.println(value);
				} else {

					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
						String value = simpleDateFormat.format(dateCellValue);
						System.out.println(value);

					} else {
						double numericCellValue = cell.getNumericCellValue();
						// we must convert this double into string
						// for that we must first convert this double into long
						// and then long into String
						long l = (long) numericCellValue;
						String value = String.valueOf(l);
						System.out.println(value);
						/*
						 * // enhanced for loop myself completed task :) for (Row row : sheet) { for
						 * (Cell cell : row) { System.out.println(cell); } }
						 */

					}

				}
			}
		}

	}

	public static void myExcelGetAllData(String sheetName,String dateFormat) {
		 sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row eachRows = sheet.getRow(i);
			for (int j = 0; j < eachRows.getPhysicalNumberOfCells(); j++) {
				Cell cell = eachRows.getCell(j);
				int type = cell.getCellType();
				if (type == 1) {
					String value = cell.getStringCellValue();

					System.out.println(value);
				} else {

					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
						String value = simpleDateFormat.format(dateCellValue);
						System.out.println(value);

					} else {
						double numericCellValue = cell.getNumericCellValue();
						// we must convert this double into string
						// for that we must first convert this double into long
						// and then long into String
						long l = (long) numericCellValue;
						String value = String.valueOf(l);
						System.out.println(value);
						/*
						 * // enhanced for loop myself completed task :) for (Row row : sheet) { for
						 * (Cell cell : row) { System.out.println(cell); } }
						 */

					}

				}
			}
		}

	}
	
	public static void myExcelGetAllAccountData(String sheetName,String dateFormat) {
		 sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row eachRows = sheet.getRow(i);
			for (int j = 0; j < eachRows.getPhysicalNumberOfCells(); j++) {
				Cell cell = eachRows.getCell(j);
				int type = cell.getCellType();
				if (type == 1) {
					String value = cell.getStringCellValue();

					System.out.println(value);
				} else {

					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateCellValue = cell.getDateCellValue();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
						String value = simpleDateFormat.format(dateCellValue);
						System.out.println(value);

					} else {
						double numericCellValue = cell.getNumericCellValue();
						// we must convert this double into string
						// for that we must first convert this double into long
						// and then long into String
						//long l = (long) numericCellValue;
						String value = String.valueOf(numericCellValue);
						System.out.println(value);
						/*
						 * // enhanced for loop myself completed task :) for (Row row : sheet) { for
						 * (Cell cell : row) { System.out.println(cell); } }
						 */

					}

				}
			}
		}

	}

}
