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
import java.util.Set;

/**
 * Test class to demonstrate automation of Hybrid android apps
 */
public class HybridAppTests {
    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    public void setContextToWebview() {
        Set<String> availableContexts = driver.getContextHandles();
        availableContexts.stream()
                .filter(context -> context.toLowerCase().contains("webview"))
                .forEach(newcontext -> driver.context(newcontext));
    }

    @BeforeTest
    public void beforeTest() {
        dc.setCapability(MobileCapabilityType.UDID, "32001da947d126cb");
        dc.setCapability(MobileCapabilityType.APP, getClass().getClassLoader().getResource("Sample_Webtrends_Hybrid_App.apk").getPath());
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.webtrends.WThybridAppSample");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HybridAppSampleActivity");
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test() {
        // Perform user events on web elements
        setContextToWebview();
        driver.findElementByLinkText("Car Insurance").click();
        Assert.assertTrue(driver.findElementByPartialLinkText("Buy Car Insurance Online").isDisplayed());

        // Perform user events on native app elements
        driver.context("NATIVE_APP");
        driver.findElement(By.id("btnOnButtonClick")).click();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
