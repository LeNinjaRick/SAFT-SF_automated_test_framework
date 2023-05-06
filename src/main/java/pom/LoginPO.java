package pom;

import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import web.ActionUtils;

import static web.ActionUtils.clickjs;

public class LoginPO extends ConfigFramework {

    //Logar no SalesForce:
    public static final By username = By.id("username");
    public static final By password = By.id("password");
    //Tela inicial:
    public static final By btnLoginSF = By.id("Login");
    // valida a organização:
    public static final By messageSandBox = By.xpath("//*[@id=\"oneHeader\"]//span[normalize-space(.)='Sandbox: TIACC']");
    // botão app launcher
    public static final By appLauncher = By.xpath("//*[@id='window']/button");
    // input de pesquisa de APPs:
    public static final By searchApp = By.xpath("//input[@placeholder='Search apps and items...']");

    public void preencherCampoUser(String user) {
        ActionUtils.isElementoPresente(getBrowser(), username, 15);
        ActionUtils.fillInput(getBrowser(), username, user, 10);
    }

    public void preencherCampoPass(String pass) {
        ActionUtils.fillInput(getBrowser(), password, pass, 10);
    }

    public void clicarBotaoLogin() {
        clickjs(getBrowser(), btnLoginSF, 10);
    }

    public void loginSalesForce(String user, String pass) {
        if (!ActionUtils.isElementoPresente(getBrowser(), username, 10)) {
            System.out.println("O browser manteve o login!");
        } else {
            preencherCampoUser(user);
            preencherCampoPass(pass);
            clicarBotaoLogin();
        }
    }


}