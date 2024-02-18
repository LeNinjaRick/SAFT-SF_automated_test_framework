package pom;

import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import web.ActionUtils;

import static pom.AppsPO.getWait;
import static web.ActionUtils.clickjs;

public class LoginPO extends ConfigFramework {

    public static  By username = By.id("username");
    public static  By password = By.id("password");
    public static  By btnLoginSF = By.id("Login");
    public void preencherCampoUser(String user) {
        ActionUtils.isElementoPresente(getBrowser(), username, getWait());
        ActionUtils.fillInput(getBrowser(), username, user, getWait());
    }
    public void preencherCampoPass(String pass) {
        ActionUtils.fillInput(getBrowser(), password, pass, getWait());
    }

    public void clicarBotaoLogin() {
        clickjs(getBrowser(), btnLoginSF, getWait());
    }

    public void loginSalesForce(String user, String pass) {
        if (!ActionUtils.isElementoPresente(getBrowser(), username, getWait())) {
            System.out.println("O browser manteve o login!");
        } else {
            preencherCampoUser(user);
            preencherCampoPass(pass);
            clicarBotaoLogin();
        }
    }


}