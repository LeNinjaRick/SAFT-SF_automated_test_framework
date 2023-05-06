package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import web.ActionUtils;

import static configUtils.ConfigFramework.getBrowser;

public class SetupPO {

    public static By messageSandBox = By.xpath("//*[@id=\"oneHeader\"]//span[normalize-space(.)='Sandbox: TIACC']");
    public static By btnEngrenagem = By.xpath("//a[contains(@class, 'menuTriggerLink')]");
    public static By btnSetup = By.xpath("//li[@id='related_setup_app_home']/a");
    //input de pesquisa:
    public static By inputSearchSetup = By.xpath("//input[@placeholder='Search Setup']");
    public static By resultadoBusca(String usuario) {
        return By.xpath("//a[@role='option']//descendant::span[@title='" + usuario + "']");
    }
    public static By iframe_perfis = By.xpath("//iframe");
    public static By btnLoginUser = By.xpath("//td[@id='topButtonRow']//input[@name='login']");
    //Validar Login
    public static By labelLoggedAs = By.xpath("//div[@data-message-id='loginAsSystemMessage']/span[normalize-space(.)='Logged in as Ricardo Suporte Cielo (ricardo.suporte.cielo@accenture.com.cielo.tiacc) | Sandbox: TIACC |']");
    public static By btnLogout = By.xpath("//a[contains(@class, 'logout')]");
    public static By btnLogoutAs = By.xpath("//a[contains(normalize-space(.), 'Log out as')]");
    public static By falhaLoginEnded = By.xpath("//lightning-formatted-text[contains(text(),'Your session has ended')]");
    public static By falhaLogin = By.xpath("//div[contains(text(),'Check your Internet connection')]");
}
