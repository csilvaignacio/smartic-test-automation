package com.vigatec.modules.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.vigatec.core.base.BasePage;
import com.vigatec.modules.company.CompanySelectionPage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CSS, using = "#input-username")
    private WebElement indentityField;

    @FindBy(how = How.CSS, using = "#input-password")
    private WebElement passwordField;

    @FindBy(how = How.CSS, using = "#login")
    private WebElement btnLogin;

    public void verifyPageLoaded() {
        waitForVisibility(btnLogin);
        waitForVisibility(passwordField);
        waitForVisibility(indentityField);
        logger.debug("Login Page verificada");
    }

    public LoginPage load() {
        load("/login");
        verifyPageLoaded();
        logger.debug("Login Page cargada");
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(indentityField, username);
        logger.debug("usuario ingresado: {} ", username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        logger.debug("password ingresado");
        return this;
    }

    public CompanySelectionPage login() {
        clickWhenReady(btnLogin);
        logger.debug("boton login clickeado");
        return new CompanySelectionPage(driver);
    }

    public Boolean isLoginButtonDisplayed() {
        return waitForVisibility(btnLogin).isDisplayed();
    }
}
