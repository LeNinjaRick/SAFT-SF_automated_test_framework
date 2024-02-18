package pom;

import configUtils.ConfigFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import web.ActionUtils;

import static java.awt.SystemColor.text;

public class AppsPO {

    public static int wait;

    public static int getWait() {
        return wait;
    }

    public static void setWait(int wait) {
        AppsPO.wait = wait;
    }


    public By detailsPage = By.xpath("//a[text()='Detalhes' or text()='Details']");
    public By relatedPage = By.xpath("//a[text()='Relacionado' or text()='Related']");

    public By loadingPage = By.xpath("//div[text()='Loading']");
    public By registraCelularLabel = By.xpath("//*[text()='Register Your Mobile Phone' or text()='Registre seu celular']");
    public By naoRegistraCelular = By.xpath("//a[text()=\"I Don't Want to Register My Phone\" or text()='Não desejo registrar meu celular']");
    public By appLauncher = By.xpath("//*[contains(@class,\"appLauncher\")]//button");
    public By inputSearchApp = By.xpath("//input[@placeholder='Pesquisar aplicativos e itens…' or @placeholder='Search apps and items...']");
    public By btnChangeViewMode = By.xpath("//button[contains(@title,'Display as')or contains(@title,'Exibir como') or contains(@title,'Select list display') or contains(@title,'Selecionar exibição de lista')]");
    public By btnChangeToTableView = By.xpath("//a/span[text()='Table' or text()='Tabela']");
    public By currentViewMode = By.xpath("//button[@title='Display as Table' or @title='Exibir como Tabela']");


    public By linkAppByDataLabel(String app) {
        return By.xpath("//one-app-launcher-menu-item//a[@data-label='" + app + "']");
    }

    public By extractTTextFromFields(String field) {
        return By.xpath("//*[@field-label='" + field + "']//slot//*[text()] | //*[contains(@class,\"test-id__field-label\") and text()='" + field + "']//ancestor::div[contains(@class,'slds-form-element_readonly')]//*[contains(@class,'field-value ')]/*[text()]");

        //
    }


    public By createNewObj = By.xpath("//a[@title='New' or @title='Criar' or contains(@title,\"Nova\") or contains(@title,\"Novo\")or contains(@title,\"New\")]");

    public By btnSave = By.xpath("//button[text()='Salvar' or text()='Save' or @title='Salvar' or @title='Save'] | //footer//span[text()='Salvar' or text()='Save' or @title='Salvar' or @title='Save']//parent::button");


    //recupera o elemento de texto clicavel por javaScript
    public By spanFields(String text) {
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    //recupera o elemento de texto button clicavel por javaScript
    public By spanFieldsButton(String text) {
        return By.xpath("//*[contains(text(),'" + text + "')]//parent::button");
    }

    //recupera o elemento de texto link clicavel por javaScript
    public By spanFieldsLink(String text) {
        return By.xpath("//*[contains(text(),'" + text + "')]//parent::a");
    }

    // campos de input da criação de objetos
    public By recordCreationFields(String inputName) {
        return By.xpath("//input[@id=//label[text()='" + inputName + "']/@for] | //input[@id=//span[text()='" + inputName + "']//parent::label/@for] | //textarea[@id=//span[text()='" + inputName + "']//parent::label/@for] | //textarea[@id=//label[text()='" + inputName + "']//parent::label/@for]");
    }

    // checkbox de input da criação de objetos
    public By checkboxCreationFields(String inputName) {
        return By.xpath("//lightning-input[not (contains(@variant, \"hidden\"))]//span[text()='" + inputName + "']");
    }

    // campos de input da criação de objetos
    public By textAreaRecordCreationFields(String inputName) {
        return By.xpath("//textarea[@id=//span[text()='" + inputName + "']//parent::label/@for] | //textarea[@id=//label[text()='" + inputName + "']//parent::label/@for]");
    }

    // campos de comboBox da criação de objetos
    public By comboBoxCreationFields(String inputName) {
        return By.xpath("//button[@id=//label[text()='" + inputName + "']/@for] | //a[@aria-describedby=//span[text()='" + inputName + "']/@id] | //select[@id=//label[text()='" + inputName + "']/@for]");
    }

    // Campo clicavel de opção do comboBox a partir do texto visivel em tela, utilizado em campos combobox
    public By comboBoxOption(String valuetext) {
        return By.xpath("//lightning-base-combobox-item[@data-value='" + valuetext + "']");
    }

    // links genericos
    public By genericLink(String title) {
        return By.xpath("//a[@title='" + title + "']");
    }

    // Campo clicavel de opção do comboBox a partir do texto visivel em tela, utilizado em campos de pesquisa de objetos dentro da criação de registro
    public By resultSearchObjRelated(String valuetext) {
        return By.xpath("//lightning-base-combobox-formatted-text[@title='" + valuetext + "'] | //div[@title='" + valuetext + "']//ancestor::a[@role='option']");
    }


    public By btnSearchRecord = By.xpath("//button[@aria-label=\"Pesquisar\" or @aria-label=\"Search\"]");
    public By inputSearchRecord = By.xpath("//div[contains(@class,\"forceSearchAssistantDialog\")]//input[contains(@placeholder,\"Pesquisar\") or contains (@placeholder,\"Search\")]");


    // retorna uma span com o texto do item pesquisado
    public By resultSearchRecord(String valuetext) {
        return By.xpath("//search_dialog-instant-result-item//*[@title='" + valuetext + "']");
    }

    // retorna uma span do objeto em tela atualmente se existir
    public By currentRecord(String valuetext) {
        return By.xpath("//force-aura-action-wrapper//lightning-formatted-text[text()='" + valuetext + "']");
    }

    public By errorCreatingRecord = By.xpath("//h2[text()='Encontramos um obstáculo.' or text()='We hit a snag.']");
    public By errorIconRecord = By.xpath("//lightning-icon[@icon-name='utility:error' and @title]");


    // procura uma mensagem de erro especifica
    public By errorSearhText(String valuetext) {
        return By.xpath("//*[text()='" + valuetext + "']");
    }


    // busca por icones de edição de campos especificos
    public By editFieldInObject(String field) {
        return By.xpath("//button[@title='Editar " + field + "' or @title='Edit " + field + "']");
    }

    public By btnListView = By.xpath("//button[@title='Selecionar um modo de exibição de lista' or @title='Select a List View']");

    public By listView(String text) {
        return By.xpath("//*[text()='" + text + "']//parent::a[@role='option']");
    }

    public By actualListView(String text) {
        return By.xpath("//h1/*[text()='" + text + "']");
    }

    public By actualApp(String text) {
        return By.xpath("//lst-breadcrumbs//*[text()='" + text + "']");
    }


    public By actualMonth = By.xpath("//h2[contains(@id,'month-title')]");


    //select do ano:
    public By selectYearPrimitive = By.xpath("//lightning-calendar//select");
    //Select select = new Select(getBrowser().findElement(selectYearPrimitive);


    public By btnShowMoreActions = By.xpath("//lightning-button-menu//span[text()='Mostrar mais ações' or text()='Show more actions']//parent::button");

    public By btnActionsInRecord(String text) {
        return By.xpath("//lightning-button/button[text()='" + text + "']");
    }

    public By linkActionsInRecord(String text) {
        return By.xpath("//span[text()='" + text + "']//parent::a");
    }

}
