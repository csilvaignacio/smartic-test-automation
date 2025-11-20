package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vigatec.core.base.BaseTest;
import com.vigatec.dataproviders.TestDataProviders;
import com.vigatec.modules.common.LoginPage;
import com.vigatec.modules.company.CompanySelectionPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;

public class LoginTest extends BaseTest {

    @Epic("Login Tests")
    @Severity(io.qameta.allure.SeverityLevel.BLOCKER)
    @Test(description = "Validando login exitoso", groups = {
            "regression" }, dataProvider = "getUserValid", dataProviderClass = TestDataProviders.class)
    public void loginWhitValidUser(String username, String password) {

        Allure.step("Cargando la pagina de login", () -> {
            new LoginPage(getDriver()).load();
            takeScreenshot("Login Page cargada");
        });

        Allure.step("Ingresando credenciales del usuario", () -> {
            takeScreenshot("Login Page - Antes del Login");
            new LoginPage(getDriver())
                    .enterUsername(username)
                    .enterPassword(password)
                    .login();
            takeScreenshot("Usuario logueado");
        });

        CompanySelectionPage comp = new CompanySelectionPage(getDriver()).load();
        Allure.step("Validando titulo 'Seleccion de empresa'", () -> {
            Assert.assertTrue(comp.getTitleString().contains("Selección de empresa"), "El titulo no es correcto");
            takeScreenshot("Titulo validado");
        });
    }

    @Epic("Login Tests")
    @Severity(io.qameta.allure.SeverityLevel.CRITICAL)
    @Test(description = "Validando login fallido con usuario invalido", groups = {
            "regression" }, dataProvider = "getUserInvalid", dataProviderClass = TestDataProviders.class)
    public void loginWithInvalidUser(String username, String password) {

        Allure.step("Cargando la pagina de login", () -> {
            new LoginPage(getDriver()).load();
            takeScreenshot("Login Page cargada");
        });

        Allure.step("Ingresando credenciales del usuario invalido", () -> {
            takeScreenshot("Login Page - Antes del Login");
            new LoginPage(getDriver())
                    .enterUsername(username)
                    .enterPassword(password)
                    .login();
            takeScreenshot("Intento de login con usuario invalido");
        });

        Allure.step("Validando que no se haya logueado", () -> {
            LoginPage loginPage = new LoginPage(getDriver()).load();
            Assert.assertTrue(loginPage.isLoginButtonDisplayed());
            takeScreenshot("Login fallido validado");
        });
    }

    @Epic("Login Tests")
    @Severity(io.qameta.allure.SeverityLevel.BLOCKER)
    @Test(description = "Validando logout de usuario", groups = {
            "regression" }, dataProvider = "getUserValid", dataProviderClass = TestDataProviders.class)
    public void logout(String username, String password) {
        Allure.step("Cargando la pagina de login", () -> {
            new LoginPage(getDriver()).load();
            takeScreenshot("Login Page cargada");
        });

        Allure.step("Ingresando datos de usuario valido", () -> {
            new LoginPage(getDriver())
                    .enterUsername(username)
                    .enterPassword(password)
                    .login();
            takeScreenshot("Usuario logeado");
        });

        Allure.step("Cerrando sesion de usuario", () -> {
            LoginPage login = new CompanySelectionPage(getDriver())
                    .clickDropdownProfile()
                    .logout();

            Assert.assertTrue(login.isLoginButtonDisplayed(),
                    "Sesion de usuario no cerrada correctamente ");
            takeScreenshot("Sesion de usuario cerrada");
        });

    }

}
