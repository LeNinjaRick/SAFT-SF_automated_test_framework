package hooks;

import configUtils.ConfigFramework;
import configUtils.Drivers;
import configUtils.PropertiesManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import stepDefinitions.APIStepDefinition;
import stepDefinitions.WebStepDefinition;

import static configUtils.Drivers.abrirBrowser;

public class Hook {

    public static void iniciarWeb() {
        String userProfile = "C:\\Users\\" + System.getenv("USERNAME") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-session-crashed-bubble");
        options.addArguments("--enable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        options.setAcceptInsecureCerts(true);
        abrirBrowser("chrome", options, "sim");
    }


    private static void clearArrays() {
        WebStepDefinition.varNameArray.clear();
        WebStepDefinition.varValueArray.clear();
        WebStepDefinition.fieldsArray.clear();
        WebStepDefinition.valuesArray.clear();
        WebStepDefinition.checkboxArray.clear();
        WebStepDefinition.actionsRecordArray.clear();
        WebStepDefinition.framesArray.clear();
        APIStepDefinition.keyNames.clear();
        APIStepDefinition.keyValues.clear();
        APIStepDefinition.jsonPathArray.clear();
        APIStepDefinition.varApiNameArray.clear();
        APIStepDefinition.varApiValuesArray.clear();
    }


    @Before
    public void init() {
        clearArrays();
    }

    @After
    public void cleanUp(Scenario scenario) {

        Drivers.closeDriver(ConfigFramework.getBrowser());
    }
}