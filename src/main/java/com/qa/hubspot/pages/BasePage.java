package com.qa.hubspot.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * @author NaveenKhunteta
 *
 */
public class BasePage {

	//public WebDriver driver;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	

	/**
	 * 
	 * @param browser
	 * @return this method will return driver on the basis of browser
	 */
	public WebDriver init_driver(Properties prop) {

		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/NaveenKhunteta/Downloads/chromedriver");
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver());
		} else if (browser.equals("ff")) {
			System.setProperty("webdriver.gecko.driver", "/Users/NaveenKhunteta/Downloads/geckodriver");
			//driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		} else {
			System.out.println(browser + " --> no browser is defined");
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);

		return getDriver();
	}
	
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}

	/**
	 * 
	 * @return this method will load the config properties, and will return prop
	 *         object
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("/Users/NaveenKhunteta/Documents/workspace/AprPOMFramework/"
					+ "src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
