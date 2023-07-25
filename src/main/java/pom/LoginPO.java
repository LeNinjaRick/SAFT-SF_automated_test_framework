package pom;

import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import web.ActionUtils;

import static web.ActionUtils.clickjs;

public class LoginPO extends ConfigFramework {

    //Logar no SalesForce:
    public static  By username = By.id("username");
    public static  By password = By.id("password");
    //Tela inicial:
    public static  By btnLoginSF = By.id("Login");
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