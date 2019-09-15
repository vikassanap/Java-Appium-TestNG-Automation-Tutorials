package com.test.mobile.qa.tests;
/**
 * @author Vikas Sanap
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/**
 * Test class to demonstrate automation of web android apps
 */
public class WebAppTests {
    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    public void beforeTest() {
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel");
        dc.setCapability(MobileCapabilityType.UDID, "32001da947d126cb");
        dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        dc.setCapability(MobileCapabilityType.PAGE_LOAD_STRATEGY, "normal");
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginToAcademy() {
        driver.get("http://www.google.com");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        driver.findElement(By.name("q")).sendKeys("google");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains("Google Search"));
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
