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

    public static By labelsComboBox = By.xpath("//label[@lightning-combobox_combobox][text()]");
    public static By labelsInputs = By.xpath("//label[@lightning-input_input][not(contains(text(),'Pesquisar'))][not(contains(text(),'Search'))][text()]");
    public static By labelsInputsSearchObj = By.xpath("//label[@lightning-groupedcombobox_groupedcombobox][not(contains(text(),'Pesquisar'))][not(contains(text(),'Search'))][text()]");
    public static By labelsTextAreas = By.xpath("//label[@lightning-textarea_textarea][text()]");
    public static By labelsDatePickers = By.xpath("//lightning-datepicker//label[contains(@class,'slds-form-element__label')][text()]");
    public static By labelsCheckBox = By.xpath("//*[@records-recordlayoutcheckbox_recordlayoutcheckbox]//*[text()]");

    public static By labelsVar = By.xpath("//*[contains(@class, 'inputLabel ')]/*[not(contains(text(),'*'))][text()]");



    public static void getTextAndReturn() {
        List<WebElement> webElementsCombo = ActionUtils.fluentWaitArray(getBrowser(), labelsComboBox, AppsPO.getWait());
        List<WebElement> webElementsInput = ActionUtils.fluentWaitArray(getBrowser(), labelsInputs, AppsPO.getWait());
        List<WebElement> webElementsInputSearchObj = ActionUtils.fluentWaitArray(getBrowser(), labelsInputsSearchObj, AppsPO.getWait());
        List<WebElement> webElementsTextArea = ActionUtils.fluentWaitArray(getBrowser(), labelsTextAreas, AppsPO.getWait());
        List<WebElement> webElementsDatePicker = ActionUtils.fluentWaitArray(getBrowser(), labelsDatePickers, AppsPO.getWait());
        List<WebElement> webElementsCheckBox = ActionUtils.fluentWaitArray(getBrowser(), labelsCheckBox, AppsPO.getWait());
        List<WebElement> webElementsVar = ActionUtils.fluentWaitArray(getBrowser(), labelsVar, AppsPO.getWait());

        for (int i = 0; webElementsCombo.size() > i; i++) {
            textsCombo.add(webElementsCombo.get(i).getText());
        }
        for (int i = 0; webElementsInput.size() > i; i++) {
            textsInput.add(webElementsInput.get(i).getText());
        }
        for (int i = 0; webElementsVar.size() > i; i++) {
            textsInput.add(webElementsVar.get(i).getText());
        }
        for (int i = 0; webElementsInputSearchObj.size() > i; i++) {
            textsInput.add(webElementsInputSearchObj.get(i).getText());
        }
        for (int i = 0; webElementsTextArea.size() > i; i++) {
            textsInput.add(webElementsTextArea.get(i).getText());
        }
        for (int i = 0; webElementsDatePicker.size() > i; i++) {
            textsInput.add(webElementsDatePicker.get(i).getText());
        }
        for (int i = 0; webElementsCheckBox.size() > i; i++) {
            textsCheckBox.add(webElementsCheckBox.get(i).getText());
        }
    }


    public static void showComandsToFillInput() {
        getTextAndReturn();
        System.out.println("\n Para preencher os campos da tela de criação de registro atual, use os comandos abaixo e crie seu proprio step definition com os comandos: \n");
        System.out.println("\n*Coloque seus valores em 'valorCampo'*\n");
        for (int i = 0; textsInput.size() > i; i++) {
            System.out.println("appsActions.fillField(\"" + textsInput.get(i) + "\", \"valorCampo\");");
        }
        for (int i = 0; textsCombo.size() > i; i++) {
            System.out.println("appsActions.fillField(\"" + textsCombo.get(i) + "\", \"valorCampo\");");
        }
        for (int i = 0; textsCheckBox.size() > i; i++) {
            System.out.println(" appsActions.clickCheckbox(\"" + textsCheckBox.get(i) + "\");");
        }
        System.out.println("\nComando para salvar a criação de registro: appsActions.saveObjCreated();\n");
        System.out.println("Caso queira, incremente com um Assert de sucesso na criação: Assert.assertFalse(\"Não foi possivel salvar o registro por decorrencia de erro no preenchimento\", appsActions.validateErrorsInRecordCreation());\n");
    }


}
