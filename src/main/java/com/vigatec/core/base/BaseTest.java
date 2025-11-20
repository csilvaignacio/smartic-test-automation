package com.vigatec.core.base;

import com.vigatec.core.drivers.DriverManagerFactory;
import com.vigatec.core.drivers.DriverManager;
import com.vigatec.core.enums.DriverType;

import io.qameta.allure.Attachment;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

public class BaseTest {
    protected ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger = LogManager.getLogger(BaseTest.class); 
    

    private void setDriverManager(DriverManager driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManager getDriverManager(){
        return this.driverManager.get();
    }

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional("CHROME") String browser,Method method){
        logger.info("INICIANDO TEST: {}", method.getName());

        browser = System.getProperty("browser",browser);
        //if (browser==null)browser="CHROME";

        setDriverManager(DriverManagerFactory.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());

        logger.info("Browser: {} | Thread: {} | Test: {}",
                browser,
                Thread.currentThread().getId(),
                method.getName());
    }

    @AfterMethod
    public synchronized void quitDriver(ITestResult result) throws InterruptedException {
        String testName = result.getName();
        long threadId = Thread.currentThread().getId();

        if (result.getStatus() == ITestResult.FAILURE){
            logger.error("❌ TEST FAILED: {} (Thread: {})", testName, threadId);
            logger.error("Error: {}", result.getThrowable().getMessage());
            
            takeScreenshot("Screenshot del error: "+testName);
        
        }else {
            logger.info("✅ TEST PASSED: {} (Thread: {})", testName, threadId);
        }

        Thread.sleep(300);

        if (getDriver()!=null) {
            getDriver().quit();
            getDriverManager().quitDriver();
            driver.remove();
        }
        

        logger.info("FINALIZANDO TEST: {}", testName+"\n");
       
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

