package com.test.mobile.qa.tests;
/**
 * @author Vikas Sanap
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/**
 * Test class to demonstrate automation of native android apps
 */
public class AppTests {
    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    public void beforeTest() {
        dc.setCapability(MobileCapabilityType.UDID, "32001da947d126cb");
        dc.setCapability(MobileCapabilityType.APP, getClass().getClassLoader().getResource("com.experitest.ExperiBank.LoginActivity.2.apk").getPath());
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Test() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
        driver.findElement(By.xpath("//*[@text='Make Payment']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("vikas");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id='countryTextField']")).sendKeys("india");
        driver.findElement(By.xpath("//*[@text='Send Payment']")).click();
        driver.findElement(By.xpath("//*[@text='Yes']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@text,'Your balance is: ')]")).isDisplayed());
        driver.findElement(By.xpath("//*[@text='Logout']")).click();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
