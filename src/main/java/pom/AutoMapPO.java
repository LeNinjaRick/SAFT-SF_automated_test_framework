package pom;

import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.ActionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoMapPO extends ConfigFramework {

    public static List<String>  textsCombo = new ArrayList<>();
    public static List<String> textsInput= new ArrayList<>();
    public static List<String> textsCheckBox= new ArrayList<>();

    public static By labelsComboBox = By.xpath("//label[@lightning-combobox_combobox][text()] | //label[contains(@for,'combobox')][text()]");
    public static By labelsInputs = By.xpath("//label[@lightning-input_input][not(contains(text(),'Pesquisar'))][not(contains(text(),'Search'))][text()] | //label[contains(@for,'input')][not(contains(@for,'combobox'))][contains(@class,'form-element__label')][text()][not(contains(text(),'Pesquisar'))][not(contains(text(),'Search'))][text()]");
    public static By labelsInputsSearchObj = By.xpath("//label[@lightning-groupedcombobox_groupedcombobox][not(contains(text(),'Pesquisar'))][not(contains(text(),'Search'))][text()]");
    public static By labelsTextAreas = By.xpath("//label[@lightning-textarea_textarea][text()]");
    public static By labelsDatePickers = By.xpath("//lightning-datepicker//label[contains(@class,'slds-form-element__label')][text()]");
    public static By labelsCheckBox = By.xpath("//*[@records-recordlayoutcheckbox_recordlayoutcheckbox]//*[text()] | //label[@for=//input[@type=\"checkbox\"]/@id][contains(@class,'inputLabel ')]/span[text()]");

    public static By labelsVar = By.xpath("//*[contains(@class, 'inputLabel ')]/*[not(contains(text(),'*'))][text()]");



    public static void getTextAndReturn() {
        List<WebElement> webElementsCombo = ActionUtils.fluentWaitArray(getBrowser(), labelsComboBox, AppsPO.getWait());
        List<WebElement> webElementsInput = ActionUtils.fluentWaitArray(getBrowser(), labelsInputs, AppsPO.getWait());
        List<WebElement> webElementsInputSearchObj = ActionUtils.fluentWaitArray(getBrowser(), labelsInputsSearchObj, AppsPO.getWait());
        List<WebElement> webElementsTextArea = ActionUtils.fluentWaitArray(getBrowser(), labelsTextAreas, AppsPO.getWait());
        List<WebElement> webElementsDatePicker = ActionUtils.fluentWaitArray(getBrowser(), labelsDatePickers, AppsPO.getWait());
        List<WebElement> webElementsCheckBox = ActionUtils.fluentWaitArray(getBrowser(), labelsCheckBox, AppsPO.getWait());
        List<WebElement> webElementsVar = ActionUtils.fluentWaitArray(getBrowser(), labelsVar, AppsPO.getWait());

        for (WebElement webElement1 : webElementsCombo) {
            String text = webElement1.getText();
            if (!textsInput.contains(text) && !textsCombo.contains(text)) {
                textsCombo.add(text);
            }
        }
        for (WebElement webElement1 : webElementsInput) {
            String text = webElement1.getText();
            if (!textsCombo.contains(text) && !textsInput.contains(text)) {
                textsInput.add(text);
            }
        }
        for (WebElement webElement1 : webElementsInputSearchObj) {
            String text = webElement1.getText();
            if (!textsCombo.contains(text) && !textsInput.contains(text)) {
                textsInput.add(text);
            }
        }
        for (WebElement webElement1 : webElementsTextArea) {
            String text = webElement1.getText();
            if (!textsCombo.contains(text) && !textsInput.contains(text)) {
                textsInput.add(text);
            }
        }
        for (WebElement webElement1 : webElementsDatePicker) {
            String text = webElement1.getText();
            if (!textsCombo.contains(text) && !textsInput.contains(text)) {
                textsInput.add(text);
            }
        }
        for (WebElement webElement1 : webElementsVar) {
            String text = webElement1.getText();
            if (!textsCombo.contains(text) && !textsInput.contains(text)) {
                textsInput.add(text);
            }
        }

        for (WebElement elementsCheckBox : webElementsCheckBox) {
            textsCheckBox.add(elementsCheckBox.getText());
        }

    }


    public static void showComandsToFillInput() {
        getTextAndReturn();
        System.out.println("\n Para preencher os campos da tela de criação de registro atual, use os comandos abaixo e crie seu proprio step definition com os comandos: \n");
        System.out.println("\n*Coloque seus valores em 'valorCampo'*\n");
        for (String s : textsInput) {
            System.out.println("appsActions.fillField(\"" + s.replace("*", "") + "\", \"valorCampo\");");
        }
        for (String s : textsCombo) {
            System.out.println("appsActions.fillField(\"" + s.replace("*", "") + "\", \"valorCampo\");");
        }
        for (String checkBox : textsCheckBox) {
            System.out.println(" appsActions.clickCheckbox(\"" + checkBox.replace("*", "") + "\");");
        }
        System.out.println("\nComando para salvar a criação de registro: appsActions.saveObjCreated();\n");
        System.out.println("Caso queira, incremente com um Assert de sucesso na criação: Assert.assertFalse(\"Não foi possivel salvar o registro por decorrencia de erro no preenchimento\", appsActions.validateErrorsInRecordCreation());\n");
    }


}
