package pom;
import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import web.ActionUtils;

import static pom.AppsPO.getWait;


public class HomePO extends ConfigFramework {
    private By loginUser = By.xpath("//img[@title='User']//parent::span");
    public void validarLogin() {
        if (!ActionUtils.isElementoPresente(getBrowser(), loginUser, getWait()/2)) {
            getBrowser().navigate().refresh();
        }
        ActionUtils.isElementoPresente(getBrowser(), loginUser, getWait());
    }
}