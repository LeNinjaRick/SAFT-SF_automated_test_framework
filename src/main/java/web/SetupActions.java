package web;

import configUtils.ConfigFramework;
import configUtils.PropertiesManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.AppsPO;
import pom.HomePO;
import pom.LoginPO;
import pom.SetupPO;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static pom.AppsPO.getWait;
import static web.ActionUtils.*;
import static web.ActionUtils.isElementoPresente;

public class SetupActions extends ConfigFramework {

    AppsPO appsPO = new AppsPO();


    public static void controlCookies() {
        By botaoDesativarCookies = By.xpath("//*[@id='cookie-controls-toggle']");
        if (isElementoPresente(getBrowser(), botaoDesativarCookies, getWait())) {
            if (getAtributteOfWebElement(getBrowser(), botaoDesativarCookies, "aria-pressed", getWait()).equalsIgnoreCase("true")) {
                By ativaCookie = By.xpath("//*[@id='cookie-controls-toggle']");
                clickjs(getBrowser(), ativaCookie, getWait());
                Assert.assertEquals("Não foi possivel habilitar os cookies do navegador!", "false", getAtributteOfWebElement(getBrowser(), botaoDesativarCookies, "aria-pressed", getWait()));
            }
        }
    }

    public void realizarLogin(String tipoUsuario) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/salesforce.properties");
        LoginPO loginPO = new LoginPO();
        HomePO homePO = new HomePO();
        String userPm = pm.getProps().getProperty("username" + tipoUsuario);
        String passPm = pm.getProps().getProperty("password" + tipoUsuario);
        if (!isElementoPresente(getBrowser(), LoginPO.username, getWait())) {
            logout();
        }
        loginPO.loginSalesForce(userPm, passPm);
        if (isElementoPresente(getBrowser(), appsPO.registraCelularLabel, getWait())) {
            clickjs(getBrowser(), appsPO.naoRegistraCelular, getWait());
        }
        int maxTentativas = 3;
        int tentativas = 0;

        while ((isElementoVisivel(getBrowser(), SetupPO.falhaLoginEnded, getWait()/ 5) || isElementoPresente(getBrowser(), SetupPO.falhaLogin, getWait()/5)) && tentativas < maxTentativas) {
            getBrowser().navigate().refresh();
            getBrowser().manage().timeouts().implicitlyWait(Duration.ofSeconds(getWait()));
            System.out.println
                    ("Sistema não logou corretamente");
        tentativas++;
        }
        homePO.validarLogin();
    }

    public static void logout() {
        By profilePic = By.xpath("//button[contains(@class,'actions__avatar')]");
        clickjs(getBrowser(), profilePic, getWait());
        clickjs(getBrowser(), SetupPO.btnLogout, getWait());
    }

}