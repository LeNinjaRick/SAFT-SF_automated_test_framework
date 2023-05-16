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

import static web.ActionUtils.*;
import static web.ActionUtils.isElementoPresente;

public class SetupActions extends ConfigFramework {

    AppsPO appsPO = new AppsPO();


    public static void controlCookies() {
        By botaoDesativarCookies = By.xpath("//*[@id='cookie-controls-toggle']");
        if (isElementoPresente(getBrowser(), botaoDesativarCookies, 20)) {
            if (getAtributteOfWebElement(getBrowser(), botaoDesativarCookies, "aria-pressed", 20).equalsIgnoreCase("true")) {
                By ativaCookie = By.xpath("//*[@id='cookie-controls-toggle']");
                clickjs(getBrowser(), ativaCookie, 10);
                Assert.assertEquals("Não foi possivel habilitar os cookies do navegador!", "false", getAtributteOfWebElement(getBrowser(), botaoDesativarCookies, "aria-pressed", 20));
            }
        }
    }

    public void realizarLogin(String tipoUsuario) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/salesforce.properties");
        LoginPO loginPO = new LoginPO();
        HomePO homePO = new HomePO();
        String userPm = pm.getProps().getProperty("username" + tipoUsuario);
        String passPm = pm.getProps().getProperty("password" + tipoUsuario);
        if (!isElementoPresente(getBrowser(), LoginPO.username, 30)) {
            logout();
        }
        loginPO.loginSalesForce(userPm, passPm);
        if (isElementoPresente(getBrowser(), appsPO.registraCelularLabel, 15)) {
            clickjs(getBrowser(), appsPO.naoRegistraCelular, 15);
        }
        while (isElementoVisivel(getBrowser(), SetupPO.falhaLoginEnded, 10) || isElementoPresente(getBrowser(), SetupPO.falhaLogin, 10)) {
            getBrowser().navigate().refresh();
            getBrowser().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println
                    ("Sistema não logou corretamente");
        }
        homePO.validarLogin();
    }

    public static void logout() {
        By profilePic = By.xpath("//button[contains(@class,'actions__avatar')]");
        clickjs(getBrowser(), profilePic, 20);
        clickjs(getBrowser(), SetupPO.btnLogout, 10);
    }

    public static void logoutAs() {
        click(getBrowser(), SetupPO.btnLogoutAs, 10);
    }

    /**
     * Método que realiza o loginAS no SalesForce.
     *
     * @param nomeUsuario Usuario que será realizado o LoginAS
     * @author guilherme.de.chaves
     */
    public static void realizarLoginAS(String nomeUsuario) throws InterruptedException {
//Verifica a Org e entra nas configurações
        Assert.assertTrue(isElementoPresente(getBrowser(), SetupPO.messageSandBox, 10));
        clickjs(getBrowser(), SetupPO.btnEngrenagem, 10);
        clickjs(getBrowser(), SetupPO.btnSetup, 10);
//Realiza a troca da aba e pesquisa o usuário que deseja fazer o LoginAs
        ArrayList<String> abas = new ArrayList<String>(getBrowser().getWindowHandles());
        getBrowser().switchTo().window(abas.get(abas.size() - 1));
        getBrowser().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fillInput(getBrowser(), SetupPO.inputSearchSetup, nomeUsuario, 20);
        clickjs(getBrowser(), SetupPO.inputSearchSetup, 10);
        clickjs(getBrowser(), SetupPO.resultadoBusca(nomeUsuario), 10);
//Entra no Frame para poder clicar no botão de login
        if (isElementoPresente(getBrowser(), SetupPO.iframe_perfis, 40)) {
            getBrowser().switchTo().frame(getBrowser().findElement(SetupPO.iframe_perfis));
        } else {
            Assert.fail();
        }
//Realiza o loginAS com o usuário desejado e verifica o usuário logado
        clickjs(getBrowser(), SetupPO.btnLoginUser, 10);
        getBrowser().switchTo().defaultContent();
        new WebDriverWait(getBrowser(), Duration.ofSeconds(5));
        Assert.assertTrue(isElementoPresente(getBrowser(), SetupPO.labelLoggedAs, 10));
    }
}