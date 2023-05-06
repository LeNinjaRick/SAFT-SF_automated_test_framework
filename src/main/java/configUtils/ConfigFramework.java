package configUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ConfigFramework {

    public ConfigFramework() {

    }
    private static WebDriver browser;
    private static Actions browserActions;

    public static WebDriver getBrowser() {
        return browser;
    }

    public static void setBrowser(WebDriver browser) {
        ConfigFramework.browser = browser;
    }

    public static Actions getBrowserActions() {
        return browserActions;
    }

    public static void setBrowserActions(Actions browserActions) {
        ConfigFramework.browserActions = browserActions;
    }


}
