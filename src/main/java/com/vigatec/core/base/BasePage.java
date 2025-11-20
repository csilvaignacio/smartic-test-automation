package com.vigatec.core.base;

import com.vigatec.utils.ConfigLoader;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("Driver no puede ser null");
        }
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getInstance().getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    protected void load(String endPoint) {
        String url = ConfigLoader.getInstance().getBaseUrl() + endPoint;
        logger.info("Navegando a: {}", url);
        driver.get(url);
    }

    protected WebElement waitForVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("WebElement no visible en el tiempo esperado");
            throw new RuntimeException("Timeout esperando visibilidad de WebElement", e);
        }
        return element;
    }

    protected void clickWhenReady(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            logger.error("WebElement no clickeable");
            throw new RuntimeException("Timeout esperando click en WebElement", e);
        }
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clickWhenReady(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            logger.debug("Click exitoso en: {}", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout esperando click en: {}", locator);
            throw new RuntimeException("Elemento no clickeable: " + locator, e);
        }
    }

    protected void type(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.debug("Texto ingresado en {}: {}", locator, text);
        } catch (TimeoutException e) {
            logger.error("Timeout ingresando texto en: {}", locator);
            throw new RuntimeException("No se pudo escribir en: " + locator, e);
        }
    }
}
