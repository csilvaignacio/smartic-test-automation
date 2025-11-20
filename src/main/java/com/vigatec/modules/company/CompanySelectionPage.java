package com.vigatec.modules.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.vigatec.core.base.BasePage;
import com.vigatec.modules.common.LoginPage;

public class CompanySelectionPage extends BasePage{

    @FindBy(how = How.CSS,using = ".mb-4")
    private WebElement mainTitle;

    @FindBy(how = How.CSS,using = ".user-name.ng-star-inserted")
    private WebElement profileDropdown;

    @FindBy(how = How.CSS,using = "a[title='Cerrar sesión']")
    private WebElement btnLogout;

    public CompanySelectionPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageLoaded(){
        waitForVisibility(mainTitle);
        logger.debug("Company Selection Page verificada");
    }

    public CompanySelectionPage load(){
        verifyPageLoaded();
        logger.debug("Company Selection Page cargada");
        return this;
    }
    
    public String getTitleString(){
        return waitForVisibility(mainTitle).getText();
    }

    public CompanySelectionPage clickDropdownProfile(){
        clickWhenReady(profileDropdown);
        logger.debug("boton profile clickeado");
        return this;
    }

    public LoginPage logout(){
        clickWhenReady(btnLogout);
        logger.debug("cerrando sesion");
        return new LoginPage(driver);
    }
}
