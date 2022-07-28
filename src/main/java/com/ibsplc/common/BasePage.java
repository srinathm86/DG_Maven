package com.ibsplc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class BasePage {
//	BaseSetup bs = new BaseSetup();
	public ExtentTest test ;
	public final static Logger logger = Logger.getLogger(BasePage.class);
	private static final int maxWaitTime = 1000;
	protected WebDriver driver;

	public BasePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.test = test;
	}
	
	// protected void enter(WebElement element, String keys){
	// if (keys == null)
	// return;
	// int tryCount = 3;
	// while(true){
	// try{
	// Thread.sleep(maxWaitTime);
	// element.clear();
	// element.sendKeys(keys);
	// break;
	// }catch(UnhandledAlertException e){
	// driver.switchTo().alert().accept();

	protected static String getPreviousMonthDate(Date date) {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);

		Date preMonthDate = cal.getTime();
		return format.format(preMonthDate);
	}

	/**
	 * This function wraps the selenium click function with a sleep time
	 *
	 * @autor A-7681
	 */

	protected void click(WebElement element) {
		try {
			Thread.sleep(maxWaitTime);
			javaScriptExecute("arguments[0].click()", element);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to click" + e);
			Assert.fail("Unable to click");
		}
	}

	/*** for creating random alpha number A-6784 ***/

	private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// private static final String RANDOM_STRING_LENGTH;

	public String generateRandomString(int rand) {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i <= rand; i++) {
			int number = getRandomNumber();

			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * This function wraps the selenium click function with a sleep time
	 *
	 * @autor A-7681
	 */

	protected void click(By by) {
		try {
			WebElement element = waitForElement(by);
			Thread.sleep(maxWaitTime);
			javaScriptExecute("arguments[0].click()", element);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to click" + e);
			Assert.fail("Unable to click");
		}
	}

	protected void clickTableRowByValue(By by, int columnNo, String value) {
		WebElement table = waitForElement(by);
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		// WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo -
		// 1);
		// List<WebElement> columns = row.findElements(By.tagName("td"));
		// String temdata=columns.get(columnNo - 1).getText();

		int totalRowCount = tableBody.findElements(By.tagName("tr")).size();

		for (int i = 0; i < totalRowCount; i++) {
			System.out.println(tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td"))
					.get(columnNo - 1).getText());
			if (tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo - 1)
					.getText().contains(value)) {

				// click(tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
				// - 1));
				try {
					waitForElement(by).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(i)
							.findElements(By.tagName("td")).get(columnNo - 1).click();
				} catch (StaleElementReferenceException e) {
					// waitForElement(by).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
					// - 1).click();
				}
				break;

			}
		}

	}

	/**
	 * This function wraps the selenium sendKeys function with a sleep time
	 *
	 * @autor A-7681
	 */

	protected void enterKeys(WebElement element, String keys) {
		if (keys == null)
			return;
		try {
			Thread.sleep(maxWaitTime);
			// element.click();
			element.clear();
			element.sendKeys(keys + Keys.TAB);
		} catch (InterruptedException e) {
			System.err.println("An interupted Exception occured" + e);
		} catch (UnhandledAlertException e) {
			try {
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException e1) {
				e1.printStackTrace();
				test.log(LogStatus.FAIL, e);
			}
		}
	}
	// if (--tryCount == 0)
	// throw e;
	// }catch(InterruptedException e){
	//
	// }
	// }
	// }

	/**
	 * This function wraps the selenium sendKeys function with a sleep time
	 *
	 * @autor A-7681
	 */

	protected void enterKeys(By by, String keys) {
		if (keys == null)
			return;
		try {
			WebElement element = waitForElement(by);

			Thread.sleep(maxWaitTime);
			while (true) {
				try {
					element.click();
					element.clear();
					element.sendKeys(keys);
					break;
				}

				catch (StaleElementReferenceException | UnhandledAlertException e) {
					if (e instanceof UnhandledAlertException) {
						try {
							driver.switchTo().alert().accept();
							logger.warn("Alert accepted");
						} catch (NoAlertPresentException e1) {

						}
					} else
						element = waitForElement(by);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to send the data" + e);
			Assert.fail("Unable to send the data");
		}
	}

	protected void enterKeysWithWait(By by, String keys) {
		if (keys == null)
			return;
		try {
			WebElement element = waitForElement(by);

			Thread.sleep(maxWaitTime);
			while (true) {
				try {
					element.click();
					element.clear();
					minWait();
					element.sendKeys(keys);
					break;
				}

				catch (StaleElementReferenceException | UnhandledAlertException e) {
					if (e instanceof UnhandledAlertException) {
						try {
							driver.switchTo().alert().accept();
							logger.warn("Alert accepted");
						} catch (NoAlertPresentException e1) {

						}
					} else
						element = waitForElement(by);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to send the data" + e);
			Assert.fail("Unable to send the data");
		}
	}

	protected void enterKeysforLogin(By by, String keys) {
		if (keys == null)
			return;
		try {
			WebElement element = waitForElement(by);

			Thread.sleep(maxWaitTime);
			while (true) {
				try {
					element.click();
					// element.clear();
					element.sendKeys(keys);
					break;
				}

				catch (StaleElementReferenceException | UnhandledAlertException e) {
					if (e instanceof UnhandledAlertException) {
						try {
							driver.switchTo().alert().accept();
							logger.warn("Alert accepted");
						} catch (NoAlertPresentException e1) {

						}
					} else
						element = waitForElement(by);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to send the data" + e);
			Assert.fail("Unable to send the data");
		}
	}

	/**
	 * Created by A-7605 This method will enter keyboard character
	 *
	 * @param by
	 * @param key
	 */
	protected void enterKeys(By by, Keys key) {
		try {
			Thread.sleep(maxWaitTime);
			waitForElement(by).sendKeys(key);
		} catch (InterruptedException e) {
			System.err.println("An interupted Exception occured" + e);
		} catch (UnhandledAlertException e) {
			try {
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Created by A-7605 This method will enter keyboard character
	 *
	 * @param element
	 * @param key
	 */
	protected void enterKeys(WebElement element, Keys key) {
		try {
			Thread.sleep(maxWaitTime);
			waitForElement(element).sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An interupted Exception occured" + e);
			test.log(LogStatus.FAIL, "Unable to send the data" + e);
			Assert.fail("Unable to send the data");
		}
	}

	/**
	 * This method check the object if not checked.
	 *
	 * @autor A-7681
	 */
	protected void check(WebElement element) {
		try {
			Thread.sleep(maxWaitTime);
			if (!waitForElement(element).isSelected()) {
				// click(element);
				element.click();
			}
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
		} catch (StaleElementReferenceException e) {
			if (!waitForElement(element).isSelected()) {
				// click(element);
				element.click();
			}
		}
	}

	/**
	 * This method check the object if not checked.
	 *
	 * @autor A-7681
	 */
	protected void check(By by) {

		try {
			Thread.sleep(maxWaitTime);
			if (!waitForElement(by).isSelected()) {
				// click(by);
				waitForElement(by).click();
			}
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
		} catch (StaleElementReferenceException e) {
			if (!waitForElement(by).isSelected()) {
				// click(element);
				waitForElement(by).click();
			}
		}
	}

	/**
	 * This method uncheck the object if checked.
	 *
	 * @autor A-7681
	 */
	protected void unCheck(WebElement element) {

		try {
			Thread.sleep(maxWaitTime);
			if (element.isSelected()) {
				element.click();
			}
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
		}
	}

	/**
	 * This method uncheck the object if checked.
	 *
	 * @autor A-7681
	 */
	protected void unCheck(By by) {

		try {
			Thread.sleep(maxWaitTime);
			if (waitForElement(by).isSelected()) {
				// click(by);
				waitForElement(by).click();
			}
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
		}
	}

	/**
	 * This method checks if the element is present in dom
	 *
	 * @param path
	 * @return rslt
	 * @autor A-7681
	 */

	protected boolean isElementPresent(By path) {
		boolean rslt = false;
		try {
			driver.findElement(path);
			rslt = true;
		} catch (Exception e) {
			rslt = false;
		}
		return rslt;
	}

	/**
	 * This is wait for 2 seconds
	 *
	 * @autor A-7681
	 */

	protected void minWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is long wait for 6 seconds
	 *
	 * @autor A-7681
	 */

	protected void maxWait() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This functions waits for the element to be present for 60 seconds before
	 * throwing the exception
	 *
	 * @param element
	 * @return
	 * @autor A-7681
	 */
	protected WebElement waitForElement(WebElement element) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(element));
				// WebElement elem =
				// wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}
		return elem;

	}

	// A-8680
	protected WebElement waitForElementUntilAttributePresent(WebElement element, String attribute, String value) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.attributeContains(element, attribute, value));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
		return elem;

	}

	/**
	 * This functions waits for the element to be present for 60 seconds before
	 * throwing the exception
	 *
	 * @param path
	 * @return
	 * @autor A-7681
	 */

	protected WebElement waitForElement(By path) {
		WebElement elem = null;
		while (true) {

			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(path));
				// WebElement elem =
				// wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(path)));
				driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
				// highlightElement(elem);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			} catch (Exception e) {
				test.log(LogStatus.FAIL, e);
				Assert.fail();
			}
		}
		return elem;
	}

	/**
	 * This functions waits for the element found by path to be visible for 60
	 * seconds before throwing the exception
	 *
	 * @param path
	 * @autor A-7681
	 */
	protected WebElement waitForElementVisible(By path) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.visibilityOfElementLocated(path));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}
		return elem;
	}

	/**
	 * This functions waits for the element found by path to be Invisible for 60
	 * seconds before throwing the exception
	 *
	 * @param path
	 * @autor A-7681
	 */
	protected void waitForElementInVisible(By path) {
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(path));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}
		return;
	}

	/**
	 * This functions waits for the element found by path to be refreshed and be
	 * clickable for 60 seconds before throwing the exception
	 *
	 * @param path
	 * @autor A-7681
	 */
	protected WebElement waitForElementRefreshed(By path) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(path)));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}
		return elem;
	}

	// protected void waitForElementEnabled(WebElement element) {
	// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	// WebDriverWait wait = new WebDriverWait(driver, 60);
	// Boolean elem = wait.until((ExpectedCondition<Boolean>) webDriver ->
	// element.isEnabled());
	// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//
	// }

	/*
	 * protected void invoiceFromDate(String AWBDate){
	 * 
	 * Calendar calendar= Calendar.getInstance(); SimpleDateFormat
	 * dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	 * 
	 * }
	 */

	/**
	 * This functions waits for the element to be enabled for 60 seconds before
	 * throwing the exception
	 *
	 * @param element
	 * @autor A-7681
	 */
	protected void waitForElementEnabled(final WebElement element) {
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				Boolean elem = wait.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						return element.isEnabled();
					}
				});
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}

	}

	/**
	 * This functions waits for the by to be disabled for 60 seconds before
	 * throwing the exception
	 *
	 * @param by
	 * @autor A-7681
	 */
	protected void waitForElementDisabled(final By by) {
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				Boolean elem = wait.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						return !(driver.findElement(by).isEnabled());
					}
				});
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}

	}

	/**
	 * This functions waits for the element to be enabled for 60 seconds before
	 * throwing the exception
	 *
	 * @param by
	 * @autor A-7681
	 */
	protected void waitForElementEnabled(final By by) {
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				Boolean elem = wait.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {
						return waitForElement(by).isEnabled();
					}
				});
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
				minWait();
			}
		}

	}

	/**
	 * Waits for the frame and switchs to the frame
	 * 
	 * @param frameLocator
	 * @autor A-7681
	 */
	protected void waitForFrameAndSwitch(String frameLocator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (UnhandledAlertException e) {
			try {
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException e1) {
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
			Assert.fail("Didn't switch to frame :  " + frameLocator + " due to exception : " + e);
		}
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

	}

	/**
	 * Waits for the frame and switchs to the frame
	 *
	 * @param frame
	 * @autor A-7681
	 */
	protected void waitForFrameAndSwitch(WebElement frame) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	/**
	 * Waits for the n number of windows to be present
	 *
	 * @param windows
	 * @autor A-7681
	 */
	protected void waitForNewWindow(int windows) {
		minWait();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.numberOfWindowsToBe(windows));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	/**
	 * verifies number of windows to be.
	 *
	 * @param windows
	 * @autor A-7681
	 */
	protected boolean verifyNumberOfWindows(int windows) {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.numberOfWindowsToBe(windows));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * Selects from dropdown based on the value
	 *
	 * @param element
	 * @param value
	 * @autor A-7681
	 */
	protected void selectByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	/**
	 * Selects from dropdown base on the Text visible
	 *
	 * @param element
	 * @param text
	 * @autor A-7681
	 */
	protected void selectByText(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	/**
	 * Selects from dropdown base on the index number
	 *
	 * @param element
	 * @param index
	 */
	protected void selectByIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * Gets the selected value
	 *
	 * @param element
	 * @return
	 * @autor A-7681
	 */
	protected String getSelectedValue(WebElement element) {
		Select sel = new Select(element);
		return sel.getFirstSelectedOption().getText();
	}

	/**
	 * Selects from dropdown based on the value
	 *
	 * @param by
	 * @param value
	 * @autor A-7681
	 */
	protected void selectByValue(By by, String value) {
		try {
			WebElement element = waitForElement(by);
			Thread.sleep(maxWaitTime);
			Select sel = new Select(element);
			sel.selectByValue(value);
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
		}
	}

	/**
	 * Selects from dropdown base on the Text visible
	 *
	 * @param by
	 * @param text
	 * @autor A-7681
	 */
	protected void selectByText(By by, String text) {
		try {
			WebElement element = waitForElement(by);
			Thread.sleep(maxWaitTime);
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("An exception Occured" + e);
			test.log(LogStatus.FAIL, e);
			Assert.fail("An exception Occured" + e);
		}
	}

	/**
	 * Selects from dropdown base on the index number
	 *
	 * @param by
	 * @param index
	 * @autor A-7681
	 */
	protected void selectByIndex(By by, int index) {
		try {
			WebElement element = waitForElement(by);
			Thread.sleep(maxWaitTime);
			Select sel = new Select(element);
			sel.selectByIndex(index);
		} catch (InterruptedException e) {
			System.out.println("An exception Occured" + e);
			test.log(LogStatus.FAIL, e);
		}
	}

	/**
	 * Gets the selected value
	 *
	 * @param by
	 * @return
	 * @autor A-7681
	 */
	protected String getSelectedValue(By by) {
		WebElement element = waitForElementVisible(by);
		Select sel = new Select(element);
		return sel.getFirstSelectedOption().getText();
	}

	/**
	 * returns all the options in the dropdown
	 *
	 * @param by
	 * @return
	 * @autor A-7681
	 */
	protected List<String> getAllValues(By by) {
		List<String> values = new ArrayList<String>();
		WebElement element = waitForElement(by);
		Select sel = new Select(element);
		List<WebElement> options = sel.getOptions();
		for (int i = 0; i < options.size(); i++) {
			values.add(i, options.get(i).getText());
		}
		return values;
	}

	/**
	 * Waits for 60 seconds for the element to be not present
	 *
	 * @param path
	 * @autor A-7681
	 */
	protected void waitForElementNotPresent(By path) {

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(path));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	/**
	 * Verifies element present by waitng 5 mins
	 *
	 * @param path
	 * @return
	 * @autor A-7681
	 */
	protected boolean verifyElementPresent(final By path) {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement elem = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return driver.findElement(path);
				}
			});
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verifies element visible by waitng 5 secs
	 *
	 * @param path
	 * @return
	 * @autor A-7681
	 */
	protected boolean verifyElementVisible(final By path) {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement elem = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return driver.findElement(path);
				}
			});
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			return elem.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verifies element enabled by waitng 5 secs
	 *
	 * @param path
	 * @return
	 * @autor A-7681
	 */
	protected boolean verifyElementEnabled(final By path) {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement elem = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver webDriver) {
					return driver.findElement(path);
				}
			});
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			return elem.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verifies element present by waitng 5 mins
	 *
	 * @param element
	 * @return
	 * @autor A-7681
	 */
	protected boolean verifyElementPresent(WebElement element) {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(element));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Hovwer on element and click on element2
	 *
	 * @param element
	 * @param element2
	 * @autor A-7681
	 */
	protected void hoverAndClick(WebElement element, WebElement element2) {

		Actions acts = new Actions(driver);
		acts.moveToElement(element).click().moveToElement(element2).click().build().perform();

	}

	/**
	 * Hovwer on element
	 *
	 * @param element
	 * @autor A-7681
	 */
	protected void hover(WebElement element) {

		Actions acts = new Actions(driver);
		acts.moveToElement(element).perform();

	}

	/**
	 * double click on element
	 *
	 * @param element
	 * @autor A-7681
	 */
	protected void doubleClick(WebElement element) {

		Actions acts = new Actions(driver);
		acts.doubleClick(element).perform();

	}

	/**
	 * switch to 2nd window closing 1st window
	 *
	 * @autor A-7681
	 */
	protected void moveToSecondWindow() {
		maxWait();
		waitForNewWindow(2);
		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String secondHandle = windowHandles[1];
		driver.close();
		driver.switchTo().window(secondHandle);

	}

	/**
	 * switch to 2nd window without closing 1st window
	 *
	 * @autor A-7681
	 */
	protected void switchToSecondWindow() {
		maxWait();
		waitForNewWindow(2);
		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String secondHandle = windowHandles[1];
		driver.switchTo().window(secondHandle);

	}

	/**
	 * switch to nth window without closing any window
	 *
	 * @param n
	 * @autor A-7681
	 */
	protected void switchToNthWindow(int n) {
		maxWait();
		waitForNewWindow(n);
		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String handle = windowHandles[n - 1];
		driver.switchTo().window(handle);

	}

	/**
	 * close the second window and move to first window
	 *
	 * @autor A-7681
	 */
	protected void closeAndmoveToFirstWindow() {

		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String firstHandle = windowHandles[0];
		driver.close();
		driver.switchTo().window(firstHandle);

	}

	/**
	 * switch to first window without explicitly closing 2nd window
	 *
	 * @autor A-7681
	 */
	protected void switchToFirstWindow() {

		waitForNewWindow(1);
		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String firstHandle = windowHandles[0];
		driver.switchTo().window(firstHandle);

	}

	/**
	 * switch to first window without waiting for second window to close
	 *
	 * @autor A-7681
	 */
	protected void switchBackToFirstWindow() {

		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String firstHandle = windowHandles[0];
		driver.switchTo().window(firstHandle);

	}

	/**
	 * check whether alert is present
	 *
	 * @return
	 * @autor A-7681
	 */

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * returns a list of webelements matching the By parameter
	 *
	 * @param by
	 * @return WebElementList
	 * @autor A-7681
	 */
	protected List<WebElement> getWebElements(By by) {

		List<WebElement> listElement;

		listElement = driver.findElements(by);

		return listElement;
	}

	/**
	 * Created by A-7605 returns the values in a coloumn
	 *
	 * @param by
	 * @param columnNo
	 * @return edited by A-7681
	 */
	protected List<String> getTableColumnValues(By by, int columnNo) {
		List<String> tableColsVals = new ArrayList<String>();
		WebElement table = waitForElement(by);
		// WebElement tableBody = table.findElement(By.tagName("tbody"));
		// int tableRowCount = tableBody.findElements(By.tagName("tr")).size();
		int tableRowCount = 0, count = 0;
		while (count < 5) {
			try {
				tableRowCount = table.findElements(By.xpath(".//tbody/tr")).size();
				break;
			} catch (StaleElementReferenceException e) {
				count++;
			}
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for (int i = 1; i <= tableRowCount; i++) {
			// String columnText =
			// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
			// - 1).getText();
			try {
				String columnText = table.findElement(By.xpath(".//tbody/tr[" + i + "]/td[" + columnNo + "]"))
						.getText();
				tableColsVals.add(columnText);
			} catch (NoSuchElementException e) {
				continue;
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return tableColsVals;
	}

	/**
	 *
	 * returns the values in a coloumn with input field
	 *
	 * @param by
	 * @param columnNo
	 * @return edited by A-7681
	 */
	protected List<String> getTableColumnValues_input(By by, int columnNo) {
		List<String> tableColsVals = new ArrayList<String>();
		WebElement table = waitForElement(by);
		// WebElement tableBody = table.findElement(By.tagName("tbody"));
		// int tableRowCount = tableBody.findElements(By.tagName("tr")).size();
		int tableRowCount = 0, count = 0;
		while (count < 5) {
			try {
				tableRowCount = table.findElements(By.xpath(".//tbody/tr")).size();
				break;
			} catch (StaleElementReferenceException e) {
				count++;
			}
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for (int i = 1; i <= tableRowCount; i++) {
			// String columnText =
			// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
			// - 1).getText();
			try {
				String columnText = table
						.findElement(By.xpath(".//tbody/tr[" + i + "]/td[" + columnNo + "]/input[@type,'text']"))
						.getAttribute("value");
				tableColsVals.add(columnText);
			} catch (NoSuchElementException e) {
				continue;
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return tableColsVals;
	}

	/**
	 * checks if a value is present in an coloumn in the given table.
	 *
	 * @param by
	 * @param colNo
	 * @param expValue
	 * @return
	 * @author Arun A-7681
	 */
	protected boolean verifyValuePresentInTblColumn(By by, int colNo, String expValue) {

		boolean flag = false;
		List<String> values = getTableColumnValues(by, colNo);
		for (String value : values) {
			if (value.toLowerCase().contains(expValue.toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * checks if a value is present in an coloumnm with input field in the given
	 * table.
	 *
	 * @param by
	 * @param colNo
	 * @param expValue
	 * @return
	 * @author Arun A-7681
	 */
	protected boolean verifyValuePresentInTblColumn_input(By by, int colNo, String expValue) {

		boolean flag = false;
		List<String> values = getTableColumnValues_input(by, colNo);
		for (String value : values) {
			if (value.toLowerCase().contains(expValue.toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * Created By A-7605 This method will return number of rows in a table
	 *
	 * @param by
	 * @return
	 */
	protected int getTableRowCount(By by) {
		WebElement table = waitForElement(by);
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int tableRowCount = tableBody.findElements(By.tagName("tr")).size();
		return tableRowCount;
	}

	/**
	 * Created By A-7605 This method will return number of rows in a table
	 *
	 * @param by
	 * @return
	 */
	protected String getTableCellValue(By by, int columnNo, int rowNo) {
		// WebElement table = waitForElement(by);
		// WebElement tableBody = table.findElement(By.tagName("tbody"));
		// WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo -
		// 1);
		// List<WebElement> columns = row.findElements(By.tagName("td"));
		// scrollToView(columns.get(columnNo - 1));
		// return columns.get(columnNo - 1).getText();

		String xpath = ".//tbody/tr[" + rowNo + "]/td[" + columnNo + "]";
		return waitForElement(by).findElement(By.xpath(xpath)).getText();

	}

	protected String getTableCellTextValue(By by, int columnNo, int rowNo) {
		List<WebElement> columns;
		try {
			WebElement table = waitForElement(by);
			WebElement tableBody = table.findElement(By.tagName("tbody"));
			WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo - 1);
			columns = row.findElements(By.tagName("td"));
			scrollToView(columns.get(columnNo - 1));
		} catch (StaleElementReferenceException e) {
			WebElement table = waitForElement(by);
			WebElement tableBody = table.findElement(By.tagName("tbody"));
			WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo - 1);
			columns = row.findElements(By.tagName("td"));
			scrollToView(columns.get(columnNo - 1));
		}
		return columns.get(columnNo - 1).getText();

	}

	protected String getTableCellTextValue_header(By by, int columnNo, int rowNo) {
		List<WebElement> columns;
		try {
			WebElement table = waitForElement(by);
			WebElement tableBody = table.findElement(By.tagName("thead"));
			WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo - 1);
			columns = row.findElements(By.tagName("td"));
			scrollToView(columns.get(columnNo - 1));
		} catch (StaleElementReferenceException e) {
			WebElement table = waitForElement(by);
			WebElement tableBody = table.findElement(By.tagName("thead"));
			WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo - 1);
			columns = row.findElements(By.tagName("td"));
			scrollToView(columns.get(columnNo - 1));
		}
		return columns.get(columnNo - 1).getText();

	}

	/**
	 * Created By A-7681 This method will return number of rows in a table with
	 * input field
	 *
	 * @param by
	 * @return
	 */
	protected String getTableCellValue_input(By by, int columnNo, int rowNo) {
		WebElement table = waitForElement(by);
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		WebElement row = tableBody.findElements(By.tagName("tr")).get(rowNo - 1);
		List<WebElement> columns = row.findElements(By.tagName("td"));
		scrollToView(columns.get(columnNo - 1));
		return columns.get(columnNo - 1).findElement(By.tagName("input")).getAttribute("value");

	}

	/**
	 * Created by A-7605 This method will return the row number of string in the
	 * table
	 *
	 * @param by
	 * @param columnNo
	 * @param value
	 * @return
	 */
	protected int getTableRowNumber(By by, int columnNo, String value) {
		WebElement table = waitForElement(by);
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int totalRowCount = tableBody.findElements(By.tagName("tr")).size();
		int rowNumber = 0;
		String xpath;
		String text;
		for (int i = 0; i < totalRowCount; i++) {
			xpath = ".//tbody/tr[" + (i + 1) + "]/td[" + columnNo + "]";
			text = table.findElement(By.xpath(xpath)).getText();
			// String text =
			// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
			// - 1).getText();
			if (text == null || text.trim().length() == 0)
				try {
					WebElement rowFirstChildElement = table
							.findElement(By.xpath(".//tbody/tr[" + (i + 1) + "]/td[" + columnNo + "]/*[1]"));
					if (rowFirstChildElement.getAttribute("value") == null) {
						// text =
						// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
						// - 1).findElement(By.xpath("/*[1]")).getText();
						// xpath =
						// ".//tbody/tr["+(i+1)+"]/td["+columnNo+"]/*[1]";
						text = rowFirstChildElement.getText();
					} else {
						// xpath =
						// ".//tbody/tr["+(i+1)+"]/td["+columnNo+"]/*[1]";
						// text =
						// table.findElement(By.xpath(xpath)).getAttribute("value");
						text = rowFirstChildElement.getAttribute("value");
						// text =
						// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
						// -
						// 1).findElement(By.xpath("/*[1]")).getAttribute("value");
					}
				} catch (Exception e) {

				}
			if (text.equalsIgnoreCase(value)) {
				rowNumber = i + 1;
				break;
			}
		}
		return rowNumber;
	}

	/**
	 * Selects a row in table base on the value in the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681 changed on 28/12
	 */
	protected void tblSelectRowByColValue(By tblLocator, int chkBxCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - chkBxCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo -
			// chkBxCol) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - chkBxCol) + "]//input[@type='checkbox']";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (chkBxCol -
			// valueClo) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (chkBxCol - valueClo) + "]//input[@type='checkbox']";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		try {
			check(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			minWait();
			table = waitForElement(tblLocator);
			check(table.findElement(By.xpath(xpath)));
		}
		// table.findElement(By.xpath(xpath)).click();
	}

	/**
	 * @param tblLocator
	 * @param chkBxCol
	 * @param valueClo1
	 * @param valueClo2
	 * @param value1
	 * @param value2
	 *            Value2>value1
	 */

	protected void tblSelectRowByMultipleColValue(By tblLocator, int chkBxCol, int valueClo1, int valueClo2,
			String value1, String value2) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo2 - chkBxCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo -
			// chkBxCol) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo1
					+ "][contains(.,'" + value1
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo2 - valueClo1) + "][contains(.,'" + value2
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo2 - chkBxCol) + "]//input[@type='checkbox']";

		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (chkBxCol -
			// valueClo) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo1
					+ "][contains(.,'" + value1
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo2 - valueClo1) + "][contains(.,'" + value2
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (chkBxCol - valueClo2) + "]//input[@type='checkbox']";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		// check(table.findElement(By.xpath(xpath)));
		table.findElement(By.xpath(xpath)).click();
	}

	/**
	 * expand a row in table base on the value in the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681
	 */
	protected void tblExpandRowByColValue(By tblLocator, int linkCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - linkCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo -
			// linkCol) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - linkCol) + "]//a";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (linkCol -
			// valueClo) + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (linkCol - valueClo) + "]//a";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		minWait();
		table.findElement(By.xpath(xpath)).click();
	}

	/**
	 * checks if a checkBox in a row in table is enabled based on the value in
	 * the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681
	 */
	protected boolean tblchkBxEnabledByColValue(By tblLocator, int chkBx, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - chkBx) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo - chkBx)
			// + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - chkBx) + "]//input";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (chkBx - valueClo)
			// + "]/input";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (chkBx - valueClo) + "]//input";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		return table.findElement(By.xpath(xpath)).isSelected();
	}

	/**
	 * gets the value in the specified coloumn in table based on the value in
	 * the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @return
	 * @autor A-7681 on 22/12 changed on 28/12
	 */
	protected void tblsetTextByColValue(By tblLocator, int txtCol, int valueClo, String value, String keys) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - txtCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo - txtCol)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - txtCol) + "]//input";

		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (txtCol - valueClo)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (txtCol - valueClo) + "]//input";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		table.findElement(By.xpath(xpath)).clear();
		table.findElement(By.xpath(xpath)).sendKeys(keys);
	}

	/**
	 * gets the value in the specified coloumn in table based on the value in
	 * the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681 on 22/12 changed on 28/12
	 */
	protected String tblGetTextByColValue(By tblLocator, int txtCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - txtCol) > 0) {
			// xpath = ".//tbody/tr/td[" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[" + (valueClo
			// - txtCol) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - txtCol) + "]";
		} else {
			// xpath = ".//tbody/tr/td[" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[" + (txtCol -
			// valueClo) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (txtCol - valueClo) + "]";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		return table.findElement(By.xpath(xpath)).getText().trim();
	}

	/**
	 * sets the value in dropdown in the specified coloumn in table based on the
	 * value in the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681 Arun
	 */
	protected void tblSetListValueByColValue(By tblLocator, int listCol, int valueClo, String value, String listValue) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - listCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo -
			// listCol) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - listCol) + "]//select";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (listCol -
			// valueClo) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (listCol - valueClo) + "]//select";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		selectByText(table.findElement(By.xpath(xpath)), listValue);
	}

	/**
	 * gets the value in dropdown in the specified coloumn in table based on the
	 * value in the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7681 Arun
	 */
	protected String tblGetListValueByColValue(By tblLocator, int listCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - listCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo -
			// listCol) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - listCol) + "]//select";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (listCol -
			// valueClo) + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (listCol - valueClo) + "]//select";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		return getSelectedValue(table.findElement(By.xpath(xpath)));
	}

	protected String tblGetTextByColValue_input(By tblLocator, int txtCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - txtCol) > 0) {

			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo - txtCol)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - txtCol) + "]";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (txtCol - valueClo)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (txtCol - valueClo) + "]";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		if (table.findElement(By.xpath(xpath)).getText() == null
				|| table.findElement(By.xpath(xpath)).getText().trim().length() == 0) {
			xpath = xpath + "//input";
			return table.findElement(By.xpath(xpath)).getAttribute("value");
		} else {
			return table.findElement(By.xpath(xpath)).getText();
		}
	}

	/**
	 * gets the value in the specified coloumn in table based on the value in
	 * the specified coloumn
	 *
	 * @param table
	 * @param value
	 * @autor A-7681 on 22/12 changed on 28/12
	 */
	protected String tblGetTextByColValue(WebElement table, int txtCol, int valueClo, String value) {

		String xpath;
		if ((valueClo - txtCol) > 0) {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (valueClo - txtCol)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - txtCol) + "]";
		} else {
			// xpath = ".//tbody/tr/td[not(@style) or
			// not(contains(@style,'display: none'))][" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[not(@style)
			// or not(contains(@style,'display: none'))][" + (txtCol - valueClo)
			// + "]";
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (txtCol - valueClo) + "]";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		return table.findElement(By.xpath(xpath)).getText();
	}

	/**
	 * USed to execute a java script and return the result
	 *
	 * @param script
	 * @return result
	 * @autor A-7681
	 */
	protected String javaScriptExecute(String script) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (String) executor.executeScript(script);
	}

	/**
	 * USed to execute a java script and return the result
	 *
	 * @param script
	 * @return result
	 * @autor A-7681
	 */
	protected String javaScriptExecute(String script, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (String) executor.executeScript(script, element);
	}

	/**
	 * Used to scroll the element located by locator in to view
	 *
	 * @param locator
	 * @return element
	 * @autor A-7681
	 */
	protected void scrollToView(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
		return;
	}

	/**
	 * Used to scroll the element in to view
	 *
	 * @param element
	 * @return element
	 * @autor A-7681
	 */
	protected void scrollToView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return;
	}

	/**
	 * Used to highlight the elements
	 *
	 * @param element
	 * @author Arun A-7681 on 27/12
	 */
	private void highlightElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: yellow; border: 2px solid red;");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

	}

	protected void tabPress(int times) {
		Actions action = new Actions(driver);

		for (int i = 0; i < times; i++) {
			action.sendKeys(Keys.TAB).perform();
		}
	}

	/**
	 * Created by A-7605 on 5-3-18 This method returns the number windows
	 * 
	 * @return
	 */
	protected int getNumberOfWindows() {
		minWait();
		return driver.getWindowHandles().size();
	}

	/**
	 * Created by A-7605 on 12-3-18 This method returns list of the name of col
	 * header in a table
	 * 
	 * @param by
	 * @return
	 */
	protected List<String> getTableHeaderColNames(By by) {
		List<String> colHeader = new ArrayList<>();
		WebElement tableHeaderRow = waitForElement(by).findElement(By.tagName("thead")).findElement(By.tagName("tr"));
		int columnNum = tableHeaderRow.findElements(By.tagName("td")).size();
		for (int i = 0; i < columnNum; i++) {
			colHeader.add(tableHeaderRow.findElements(By.tagName("td")).get(i).getText());
		}
		return colHeader;
	}

	/**
	 * converting date from one format to another
	 * 
	 * @author A-8260
	 * @param original
	 * @param target
	 * @param parseDate
	 * @return
	 */
	public static String dateFormatCoverter(String original, String target, String parseDate) {
		DateFormat originalFormat = new SimpleDateFormat(original, Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat(target);
		Date date = new Date();
		try {
			date = originalFormat.parse(parseDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formattedDate = targetFormat.format(date);
		return formattedDate;
	}

	/**
	 * java script executor getText
	 * 
	 * @author A-8260
	 */
	protected String getText_JS(By by) {
		WebElement element = null;
		try {
			element = waitForElement(by);
			Thread.sleep(maxWaitTime);

		} catch (InterruptedException e) {
			System.err.println("An interupted Exception occured" + e);
		}
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", element);
	}

	/**
	 * clicks on a value at specified column
	 * 
	 * @author A-8260
	 * @return
	 */
	protected void tblClickByColValue(By tblLocator, int value) {
		WebElement table = waitForElement(tblLocator);
		String xpath;
		xpath = ".//tbody/tr/td[" + value + "]//input";
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		// System.out.println(table.findElement(By.xpath(xpath)).getText());
		// System.out.println(table.findElement(By.xpath(xpath)).toString());
		table.findElement(By.xpath(xpath)).click();

	}

	protected void tblClickByColInput(By tblLocator, int value) {
		WebElement table = waitForElement(tblLocator);
		String xpath;
		xpath = ".//tbody/tr/td[" + value + "]//input";
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			// table.findElement(By.xpath(xpath)).click();
		}
		// System.out.println(table.findElement(By.xpath(xpath)).getText());
		// System.out.println(table.findElement(By.xpath(xpath)).toString());
		try {
			check(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			minWait();
			table = waitForElement(tblLocator);
			check(table.findElement(By.xpath(xpath)));
		}
		check(table.findElement(By.xpath(xpath)));

	}

	/**
	 * clicks on a value at specified column
	 * 
	 * @author A-8260
	 * @param tblLocator
	 * @param txtCol
	 * @param valueClo
	 * @param value
	 * @return
	 */
	protected WebElement tblClickByColValWithRef(By tblLocator, int txtCol, int valueClo, String value) {
		WebElement table = waitForElement(tblLocator);
		String xpath;

		if ((valueClo - txtCol) > 0) {
			// xpath = ".//tbody/tr/td[" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/preceding-sibling::td[" + (valueClo
			// - txtCol) + "]";
			xpath = "./tbody/tr/td[" + valueClo + "][contains(.,'" + value + "')]/preceding-sibling::td["
					+ (valueClo - txtCol) + "]";
			// xpath = "./tbody/tr/td[" + valueClo + "][contains(.,'" + value +
			// "')]/preceding-sibling::td[" + (valueClo - txtCol) + "]//input";
		} else {
			// xpath = ".//tbody/tr/td[" + valueClo +
			// "]/descendant-or-self::node()[contains(text(),'" + value +
			// "')]/ancestor-or-self::node()/following-sibling::td[" + (txtCol -
			// valueClo) + "]";
			xpath = "./tbody/tr/td[" + valueClo + "][contains(.,'" + value + "')]/following-sibling::td["
					+ (txtCol - valueClo) + "]";
			// xpath = "./tbody/tr/td[" + valueClo + "][contains(.,'" + value +
			// "')]/following-sibling::td[" + (txtCol - valueClo) + "]//input";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
		}
		table.findElement(By.xpath(xpath)).click();

		return table.findElement(By.xpath(xpath));
	}

	/*
	 * A-8255
	 */
	public String getTableColumnbyRow(By by, int columnNo, int row) {

		String tableColsVals = null;
		WebElement table = waitForElement(by);
		try {
			tableColsVals = table.findElement(By.xpath("./tbody/tr[" + row + "]/td[" + columnNo + "]")).getText();

		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return tableColsVals;
	}

	/*
	 * A-8255
	 */
	public String[] getTableColumns(By by, int columnNo) {

		// String tableColsVals[]=new String[12];
		WebElement table = waitForElement(by);
		// WebElement tableBody = table.findElement(By.tagName("tbody"));
		// int tableRowCount = tableBody.findElements(By.tagName("tr")).size();
		int tableRowCount = 0, count = 0;
		while (count < 5) {
			try {
				tableRowCount = table.findElements(By.xpath("./tbody/tr")).size();
				break;
			} catch (StaleElementReferenceException e) {
				count++;
			}
		}

		String tableColsVals[] = new String[tableRowCount + 1];

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for (int i = 1; i <= tableRowCount; i++) {
			// String columnText =
			// tableBody.findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(columnNo
			// - 1).getText();
			try {
				String columnText = table.findElement(By.xpath("./tbody/tr[" + i + "]/td[" + columnNo + "]")).getText();
				tableColsVals[i] = columnText;

			} catch (NoSuchElementException e) {
				continue;
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return tableColsVals;
	}

	/**
	 * A-8260 move to Widow
	 */
	protected void moveToWindow() {
		String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
		String currentHandle = windowHandles[0];
		driver.switchTo().window(currentHandle);

	}

	/**
	 * @author A-8260
	 * @param element
	 *            Waits until the staleness of the element and then identifies
	 *            it.
	 * @return
	 */
	protected WebElement waitForElementStaleness(WebElement element) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(element));
				// WebElement elem =
				// wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (StaleElementReferenceException e) {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(element));
				minWait();
			}
		}
		return elem;
	}

	protected WebElement waitForElementStaleness(By by) {
		WebElement elem = null;
		while (true) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(by));
				// WebElement elem =
				// wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			} catch (StaleElementReferenceException e) {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				elem = wait.until(ExpectedConditions.elementToBeClickable(by));
				minWait();
			}
		}
		return elem;
	}

	/**
	 * Created by A-7605 on 19-4-2018 This method wait unill the page completely
	 * load
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
	}

	/**
	 * @author A-8260
	 * @param by
	 * @param value
	 */
	public void inputText_JS(By by, String value) {
		WebElement element = null;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {

			element = waitForElement(by);
			jse.executeScript("arguments[0].value='" + value + "';", element);
			Thread.sleep(maxWaitTime);
		} catch (InterruptedException e) {
			System.err.println("An interupted Exception occured" + e);
		}

	}

	/**
	 * gets the value in the specified coloumn in table based on the value in
	 * the specified coloumn
	 *
	 * @param tblLocator
	 * @param value
	 * @autor A-7605 on 03/05/18
	 */
	protected String tblGetEditableTextByColValue(By tblLocator, int txtCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - txtCol) > 0) {
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - txtCol) + "]";
		} else {
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (txtCol - valueClo) + "]";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		return table.findElement(By.xpath(xpath + "//input")).getAttribute("value").trim();
	}

	/**
	 * Created by A-7605 This method returns attribute value of a table field
	 * 
	 * @param tblLocator
	 * @param fieldCol
	 * @param valueClo
	 * @param value
	 * @param attribute
	 * @return
	 */
	protected String tblGetFieldValueByColValue(By tblLocator, int fieldCol, int valueClo, String value) {

		WebElement table = waitForElement(tblLocator);

		String xpath;
		if ((valueClo - fieldCol) > 0) {
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/preceding-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (valueClo - fieldCol) + "]/input";
		} else {
			xpath = ".//tbody/tr/td[not(@style) or not(contains(@style,'display: none'))][" + valueClo
					+ "][contains(.,'" + value
					+ "')]/following-sibling::td[not(@style) or not(contains(@style,'display: none'))]["
					+ (fieldCol - valueClo) + "]/input";
		}
		try {
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		} catch (StaleElementReferenceException e) {
			table = waitForElement(tblLocator);
			scrollToView(table.findElement(By.xpath(xpath)));
			minWait();
		}
		return table.findElement(By.xpath(xpath)).getAttribute("value");
	}

	/**
	 * Created by A-7605 on 14-5-18 This method returns lists of table column
	 * (of input types) values
	 * 
	 * @param by
	 * @param columnNo
	 * @return
	 */
	protected List<String> getTableInputColumnValues(By by, int columnNo) {
		List<String> tableColsVals = new ArrayList<String>();
		WebElement table = waitForElement(by);
		int tableRowCount = 0, count = 0;
		while (count < 5) {
			try {
				tableRowCount = table.findElements(By.xpath(".//tbody/tr")).size();
				break;
			} catch (StaleElementReferenceException e) {
				count++;
			}
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for (int i = 1; i <= tableRowCount; i++) {
			try {
				String columnText = table.findElement(By.xpath(".//tbody/tr[" + i + "]/td[" + columnNo + "]/input"))
						.getAttribute("value");
				tableColsVals.add(columnText);
			} catch (NoSuchElementException e) {
				continue;
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return tableColsVals;
	}

	/**
	 * Created by A-7605 on 23-5-18 This method wait until the current action is
	 * processed
	 * 
	 * @param driver
	 */
	public void waitForPageActionsToComplete(WebDriver driver) {
		logger.info("Waiting for mouse cursor to be arrow");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Function<WebDriver, Boolean> pageLoaded = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				logger.info("Checking mouse cursor");
				return (webDriver.findElement(By.tagName("body")).getCssValue("cursor") != "wait");
			}

		};
		wait.until(pageLoaded);
		logger.info("Mouse cursor is now arrow");
	}

	/**
	 * checks Till white screen is available
	 * 
	 * @author Shalini
	 */
	public void waitForWhiteScreen() {
		driver.switchTo().frame("iCargoContentFrame");
		int countWait = 20;
		try {
			while (!driver.findElement(By.xpath("//body/div[@class='iCargoContent']")).isDisplayed()
					&& countWait <= 300) {
				System.out.println("Screen is up...");
				break;
			}
		} catch (Exception e) {
			countWait++;
			waitForWhiteScreen();

		}
		driver.switchTo().defaultContent();

	}

	/*
	 * try{ WebElement ele =
	 * driver.findElement(By.xpath("//body/div[@class='iCargoContent']"));
	 * driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 * WebDriverWait wait = new WebDriverWait(driver, 60);
	 * ele=wait.until(ExpectedConditions.elementToBeClickable(ele));
	 * System.out.println("Screen is up...");
	 * 
	 * } catch(Exception e){ waitForWhiteScreen(); }
	 */

	/**
	 * modifies specific string in message file
	 * 
	 * @author Sharath
	 */

	public void modifyFile(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);
			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * modifies specific string in message file
	 * 
	 * @author Sharath
	 */

	public void modifyFileReplaceOnly(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replace(oldString, newString);
			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// A-8680
	protected String getAttributebyValue(By by) {
		try {
			WebElement ele = driver.findElement(by);
			return ele.getAttribute("value");
		} catch (Exception e) {
			System.out.println("An exception Occured" + e);
			test.log(LogStatus.FAIL, "An exception Occured" + e);
			return null;
		}
	}

	// A-8680
	protected String getAttributebyValue(WebElement ele) {
		try {
			// WebElement ele = driver.findElement(by);
			return ele.getAttribute("value");
		} catch (Exception e) {
			System.out.println("An exception Occured" + e);
			test.log(LogStatus.FAIL, "An exception Occured" + e);
			return null;
		}
	}

	/**
	 * @author Sharath
	 */
	public void waitForelement() {
		int countWait = 20;
		try {
			while (driver.findElement(By.xpath("//input[@name='origin']")).isEnabled() || countWait <= 300) {
				System.out.println("Element is displayed");
				break;
			}
		} catch (Exception e) {
			countWait++;
			waitForelement();
		}
	}

	public void captureAndAddScreenshot() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			String path = dtf.format(now).toString().replaceAll(" ", "").replaceAll("/", ".").replaceAll(":", "")
					+ ".png";
//			this.driver = InitialSetup.driver;
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./Reports/ScreenShots/" + path));
			System.out.println("screenshot captured");
			String dir = System.getProperty("user.dir") + "\\Reports\\Screenshots\\";
			test.log(LogStatus.INFO, test.addScreenCapture(dir + path));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// Sharath
	public List<Map<String, String>> getRawData(String path) throws IOException {

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		Row headerRow = sh.getRow(0);
		Row dataRow;
		String key, value;
		List<Map<String, String>> mylists = new ArrayList<>();
		int noOfRows = sh.getLastRowNum();
		int noOfCols = sh.getRow(0).getLastCellNum();
		HashMap<String, String> myMap = null;
		for (int i = 1; i <= noOfRows; i++) {
			myMap = new HashMap<>();
			dataRow = sh.getRow(i);
			for (int j = 0; j < noOfCols; j++) {
				key = CellUtil.getCell(headerRow, j).getStringCellValue();
				value = CellUtil.getCell(dataRow, j).getStringCellValue();
				myMap.put(key, value);
			}
			mylists.add(myMap);
		}
		fis.close();
		return mylists;
	}
	
	/*
	 * Author- Sharath 
	 * Purpose: Method to Set all Flag status to No status
	 * Date: 29-03-2019
	 */
		public void writeDatatoExcelCell(String path, int index, String dataToWrite) throws Exception {  
			try {
	            File f1 = new File(path);
	            FileInputStream ios1 = new FileInputStream(f1);
	            XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	            XSSFSheet sheet1 = workbook1.getSheetAt(0);
	            int rows = sheet1.getLastRowNum();
	            Row row1 = sheet1.getRow(index);
	            int cellIndex = row1.getLastCellNum();
	            Cell cell1 = row1.createCell(cellIndex);
	            cell1.setCellValue(dataToWrite);
	            FileOutputStream out = new FileOutputStream(new File(path));
	            workbook1.write(out);
	            out.close();
	            out.flush();
	      } catch (Exception e) {
	    	   System.out.println("Setting flags to No failed");
	            e.printStackTrace();
	      }
		}
	
		/*
		 * Author- Sharath 
		 * Purpose: Method to Set all Flag status to No status
		 * Date: 29-03-2019
		 */
			public void writeAWBtoExcelCell(String path, int index, String dataToWrite) throws Exception {  
				try {
		            File f1 = new File(path);
		            FileInputStream ios1 = new FileInputStream(f1);
		            XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
		            XSSFSheet sheet1 = workbook1.getSheetAt(0);
		            int rows = sheet1.getLastRowNum();
		            Row row1 = sheet1.getRow(index);
		            Cell cell1 = row1.createCell(32);
		            cell1.setCellValue(dataToWrite);
		            FileOutputStream out = new FileOutputStream(new File(path));
		            workbook1.write(out);
		            out.close();
		            out.flush();
		      } catch (Exception e) {
		    	   System.out.println("Setting flags to No failed");
		            e.printStackTrace();
		      }
			}
}
