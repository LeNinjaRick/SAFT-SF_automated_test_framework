package web;

import api.ApiUtils;
import api.RegexMatcher;
import configUtils.ConfigFramework;
import configUtils.PropertiesManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import pom.AppsPO;

import java.time.Duration;

import static configUtils.Drivers.abrirBrowser;
import static pom.AppsPO.getWait;
import static web.ActionUtils.*;

public class AppsActions extends ConfigFramework {

    AppsPO appsPO = new AppsPO();
    private static PropertiesManager pm = new PropertiesManager("src/test/resources/properties/salesforce.properties");

    public void explicitWaitAndRefresh(By by) {
        long totalTime = (long) (getWait() * 1.5);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getBrowser())
                .withTimeout(Duration.ofSeconds(totalTime))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(driver -> {
            return getBrowser().findElement(by);
        });
        System.out.println("Elemento presente, iniciando refresh de navegador");
        getBrowser().navigate().refresh();
    }

    public void searchApp(String app) {
        clickjs(getBrowser(), appsPO.appLauncher, getWait());
        fillInput(getBrowser(), appsPO.inputSearchApp, app, getWait());
        clickjs(getBrowser(), appsPO.linkAppByDataLabel(app), getWait());
    }

    public void changeViewMode() {
        if (!isElementoPresente(getBrowser(), appsPO.currentViewMode, getWait())) {
            clickjs(getBrowser(), appsPO.btnChangeViewMode, getWait());
            clickjs(getBrowser(), appsPO.btnChangeToTableView, getWait());
        }

    }

    public void createObject() {
        clickjs(getBrowser(), appsPO.createNewObj, getWait());
    }


    public void fillTextField(String field, String value) {
        fillInputjs(getBrowser(), appsPO.recordCreationFields(field), value, getWait());
    }

    public void chooseValueInComboBox(String comboBoxName, String value) {
        clickjs(getBrowser(), appsPO.comboBoxCreationFields(comboBoxName), getWait());
        clickjs(getBrowser(), appsPO.comboBoxOption(value), getWait());
    }

    public void chooseValueInLinkComboBox(String comboBoxName, String value) {
        clickjs(getBrowser(), appsPO.comboBoxLinkCreationFields(comboBoxName), getWait());
        clickjs(getBrowser(), appsPO.genericLink(value), getWait());
    }


    public void chooseParentObject(String fieldname, String result) {
        fillInput(getBrowser(), appsPO.recordCreationFields(fieldname), result, getWait());
        clickjs(getBrowser(), appsPO.resultSearchObjRelated(result), getWait());
    }

    public void saveObjCreated() {
        if (isElementoPresente(getBrowser(), appsPO.btnSaveModal, getWait() / 2)) {
            clickjs(getBrowser(), appsPO.btnSaveModal, getWait());
        } else {
            clickjs(getBrowser(), appsPO.btnSave, getWait());
        }
    }

    // Metodo que lê o tipo de campo antes de preencher, durante a criação de um registro novo
    public void fillField(String fieldname, String value) {
        value = value.replace("Random", ApiUtils.getRandomPass(5));
        if (isElementoPresente(getBrowser(), appsPO.recordCreationFields(fieldname), getWait())) {
            fillInput(getBrowser(), appsPO.recordCreationFields(fieldname), value, getWait());
            if (isElementoPresente(getBrowser(), appsPO.resultSearchObjRelated(value), getWait() / 2)) {
                clickjs(getBrowser(), appsPO.resultSearchObjRelated(value), getWait());
            } else if (isElementoPresente(getBrowser(), appsPO.resultSearchObjRelatedByDiv(value), getWait() / 2)) {
                clickjs(getBrowser(), appsPO.resultSearchObjRelatedByDiv(value), getWait());
            }
        } else if (isElementoPresente(getBrowser(), appsPO.recordCreationFieldsSpan(fieldname), getWait())) {
            fillInput(getBrowser(), appsPO.recordCreationFieldsSpan(fieldname), value, getWait());
            if (isElementoPresente(getBrowser(), appsPO.resultSearchObjRelated(value), getWait() / 2)) {
                clickjs(getBrowser(), appsPO.resultSearchObjRelated(value), getWait());
            } else if (isElementoPresente(getBrowser(), appsPO.resultSearchObjRelatedByDiv(value), getWait() / 2)) {
                clickjs(getBrowser(), appsPO.resultSearchObjRelatedByDiv(value), getWait());
            }
        } else if (isElementoPresente(getBrowser(), appsPO.comboBoxCreationFields(fieldname), getWait())) {
            chooseValueInComboBox(fieldname, value);
        } else if (isElementoPresente(getBrowser(), appsPO.comboBoxLinkCreationFields(fieldname), getWait())) {
            chooseValueInLinkComboBox(fieldname, value);
        } else if (isElementoPresente(getBrowser(), appsPO.textAreaRecordCreationFields(fieldname), getWait())) {
            fillInputjs(getBrowser(), appsPO.textAreaRecordCreationFields(fieldname), value, getWait());
        } else {
            Assert.fail("Campo de preenchimento não presente na tela!");
        }
    }


    //realiza uma ação dentro do objeto atual:
    public void executeFlowAction(String actionName) {
        if (isElementoPresente(getBrowser(), appsPO.btnActionsInRecord(actionName), getWait())) {
            clickjs(getBrowser(), appsPO.btnActionsInRecord(actionName), getWait());
        } else if (isElementoPresente(getBrowser(), appsPO.btnShowMoreActions, getWait())) {
            clickjs(getBrowser(), appsPO.btnShowMoreActions, getWait());
            clickjs(getBrowser(), appsPO.linkActionsInRecord(actionName), getWait());
        } else {
            Assert.fail("O Botão de ação não esta disponivel no objeto atual!");
        }
    }


    public void searchAndClickRecord(String recordName) {
        clickjs(getBrowser(), appsPO.btnSearchRecord, getWait());
        fillInput(getBrowser(), appsPO.inputSearchRecord, recordName, getWait());
        clickjs(getBrowser(), appsPO.resultSearchRecord(recordName), getWait());
        if (isElementoPresente(getBrowser(), appsPO.currentRecord(recordName), getWait())) {
            System.out.println("Registro encontrado com sucesso!");
        } else {
            System.out.println("Não foi possivel entrar no registro!");
        }
    }


    public boolean validateErrorsInRecordCreation() {
        return isElementoPresente(getBrowser(), appsPO.errorIconRecord, getWait());
    }


    public boolean validateTextErrors(String text) {
        return isElementoPresente(getBrowser(), appsPO.errorSearhText(text), getWait());
    }


    public void validateRecordFields(String field, String value) {
        String text;
        if (isElementoPresente(getBrowser(), appsPO.detailsPage, (getWait()*3)/2)) {
            clickjs(getBrowser(), appsPO.detailsPage, getWait());
        }
        if (isElementoPresente(getBrowser(), appsPO.extractTTextFromFields(field), (getWait()*3)/2)) {
            text = getText(getBrowser(), appsPO.extractTTextFromFields(field), getWait());
        } else if (isElementoPresente(getBrowser(), appsPO.extractTTextFromFieldsSpan(field), (getWait()*3)/2)) {
            text = getText(getBrowser(), appsPO.extractTTextFromFieldsSpan(field), getWait());
        } else {
            text = "";
            System.out.println("O campo esta sem valor nenhum no salesforce!");
        }
        Assert.assertThat("O campo a ser validado não estava com o valor fornecido!", text, RegexMatcher.matchesRegex(value));
    }

    public String createVariablesByFields(String field) {
        String text;
        if (isElementoPresente(getBrowser(), appsPO.detailsPage, getWait())) {
            clickjs(getBrowser(), appsPO.detailsPage, getWait());
        }
        if (isElementoPresente(getBrowser(), appsPO.extractTTextFromFields(field), getWait())) {
            text = getText(getBrowser(), appsPO.extractTTextFromFields(field), getWait());
        } else if (isElementoPresente(getBrowser(), appsPO.extractTTextFromFieldsSpan(field), getWait())) {
            text = getText(getBrowser(), appsPO.extractTTextFromFieldsSpan(field), getWait());
        } else {
            text = "";
            System.out.println("O campo esta sem valor nenhum no salesforce!");
        }
        return text;
    }


    public void editFields(String field, String value) {
        clickjs(getBrowser(), appsPO.editFieldInObject(field), getWait());
        fillField(field, value);
        clickjs(getBrowser(), appsPO.btnSave, getWait());
    }


    public void searchRecordUrl(String id) {
        String url = pm.getProps().getProperty("urlSalesForce");
        getBrowser().get(url + id);
        getBrowser().navigate().refresh();
    }

    public void switchListView(String listView) {
        clickjs(getBrowser(), appsPO.btnListView, getWait());
        clickjs(getBrowser(), appsPO.listView(listView), getWait());
    }

    //12/12/2000
    public void enterDateInField(String date) {
        String day = date.substring(0, 1);
        String month = date.substring(3, 4);
        String year = date.substring(6, 9);
        //select the year
        Select select = new Select(getBrowser().findElement(appsPO.selectYearPrimitive));
        select.selectByVisibleText(year);

        //select the month

    }

    public void clickCheckbox(String checkBoxname) {
        clickjs(getBrowser(), appsPO.checkboxCreationFields(checkBoxname), getWait());
    }

    public void clickByText(String text) {
        if (isElementoPresente(getBrowser(), appsPO.spanFieldsButton(text), getWait() / 2)) {
            clickjs(getBrowser(), appsPO.spanFieldsButton(text), getWait());
        } else if (isElementoPresente(getBrowser(), appsPO.spanFieldsLink(text), getWait() / 2)) {
            clickjs(getBrowser(), appsPO.spanFieldsLink(text), getWait());
        } else {
            clickjs(getBrowser(), appsPO.spanFields(text), getWait());
        }
    }

    @Test
    public void recoverHTMLPageSource() {
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

        String url = pm.getProps().getProperty("urlSalesForce");
        getBrowser().navigate().to(url);
        String pageSource = getBrowser().getPageSource();

        System.out.println("page source: \n" + pageSource);
        String[] substring = pageSource.split("");
    }


}
