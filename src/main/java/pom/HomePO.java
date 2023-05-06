package pom;
import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import web.ActionUtils;

public class HomePO extends ConfigFramework {
    private final By loginUser = By.xpath("//img[@title='User']//parent::span");
    public void validarLogin() {
        if (!ActionUtils.isElementoPresente(getBrowser(), loginUser, 15)) {
            getBrowser().navigate().refresh();
        }
        ActionUtils.isElementoPresente(getBrowser(), loginUser, 30);
    }
}