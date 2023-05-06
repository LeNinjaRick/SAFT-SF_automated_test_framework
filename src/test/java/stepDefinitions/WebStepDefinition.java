package stepDefinitions;

import api.ApiUtils;
import configUtils.ConfigFramework;
import configUtils.PropertiesManager;
import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.AppsPO;
import web.AppsActions;
import web.SetupActions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static web.ActionUtils.clickjs;
import static web.ActionUtils.isElementoPresente;

public class WebStepDefinition extends ConfigFramework {


    private PropertiesManager pm = new PropertiesManager("src/test/resources/properties/salesforce.properties");
    private String url = pm.getProps().getProperty("urlSalesForce");
    SetupActions setupActions = new SetupActions();
    AppsActions appsActions = new AppsActions();

    List<String> checkboxArray = new ArrayList<>();
    List<String> actionsRecordArray = new ArrayList<>();

    List<String> fieldsArray = new ArrayList<>();
    List<String> valuesArray = new ArrayList<>();

    String objectSalesforce;
    String testType;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    List<String> varNameArray = new ArrayList<>();
    List<String> varValueArray = new ArrayList<>();


    public String getObjectSalesforce() {
        return objectSalesforce;
    }

    public void setObjectSalesforce(String objectSalesforce) {
        this.objectSalesforce = objectSalesforce;
    }

    @Given("que esteja logado no SalesForce com sucesso com o usuario {string}")
    public void queEstejaLogadoNoSalesForceComSucessoComOUsuario(String user) {
        Hook.iniciarWeb();
        WebElement botaoDesativarCookies = (new WebDriverWait(getBrowser(), Duration.ofSeconds(20)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='cookie-controls-toggle']")));
        if (botaoDesativarCookies.getAttribute("aria-pressed").equalsIgnoreCase("true")) {
            By ativaCookie = By.xpath("//*[@id='cookie-controls-toggle']");
            clickjs(getBrowser(), ativaCookie, 10);
            Assert.assertEquals("Não foi possivel habilitar os cookies do navegador!", "false", botaoDesativarCookies.getAttribute("aria-pressed"));
        }
        getBrowser().get(url);
        setupActions.realizarLogin(user);
    }

    @Given("que o tempo de espera medio sera de {int} segundos")
    public void queOTempoDeEsperaMedioSeraDeSegundos(int tempoEspera) {
        AppsPO.setWait(tempoEspera);
    }

    @And("Accessar o aplicativo {string}")
    public void accessarOAplicativo(String app) {
        appsActions.searchApp(app);
    }

    @When("Accesar o objeto {string} e criar um novo registro")
    public void accesarOObjetoECriarUmNovoRegistro(String app) {
        setObjectSalesforce(app);
        appsActions.searchApp(app);
        appsActions.changeViewMode();
        appsActions.createObject();
    }

    @And("preencher o campo {string} com o valor {string}")
    public void preencherOCampoComOValor(String field, String value) {
        fieldsArray.add(field);
        valuesArray.add(value);
        appsActions.fillField(field, value);
    }

    @And("Salvar a criacao do registro")
    public void salvarACriacaoDoRegistro() {
        appsActions.saveObjCreated();
    }

    @Then("Salvar a criacao do registro com sucesso")
    public void salvarACriacaoDoRegistroComSucesso() {
        appsActions.saveObjCreated();
        setTestType("positivo");
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @Then("Salvar a criacao do registro com falha")
    public void salvarACriacaoDoRegistroComFalha() {
        appsActions.saveObjCreated();
        setTestType("negativo");
        Assert.assertTrue("O erro na criação de registro não apareceu!", appsActions.validateErrorsInRecordCreation());
    }

    @And("busca pela mensagem de erro {string}")
    public void buscaPelaMensagemDeErro(String error) {
        Assert.assertTrue("O erro procurado não foi encontrado em tela!", appsActions.validateTextErrors(error));
    }

    @When("acessar o registro {string}")
    public void acessarORegistro(String record) {
        setObjectSalesforce(record);
        appsActions.searchAndClickRecord(record);
    }

    @Then("espero que o campo {string} esteja com o valor {string}")
    public void esperoQueOCampoEstejaComOValor(String field, String value) {
        fieldsArray.add(field);
        valuesArray.add(value);
        appsActions.validateRecordFields(field, value);
    }

    @And("simplifique o teste de preenchimento")
    public void simplifiqueOTesteDePreenchimento() {
        System.out.println("#-------------------------------------------------------------------------------------------#");
        System.out.println("Para criar um Step simplificado dos steps fornecidos de criação de registro, crie um step no gherkin de sua preferencia(exemplo: 'Given que seja criado o objeto conta predefinido') \n e depois no step definition, adicione o codigo abaixo: \n");
        System.out.println("se for a segunda vez que estiver simplificando, use apenas o codigo dos campos preenchidos na parte desejada. \n");
        System.out.println("se for fazer em um novo step definition, crie uma instancia fora do metodo da classe de AppActions: \n  AppsActions appsActions = new AppsActions();\n");
        System.out.println("\n Adicione o codigo abaixo no metodo de stepDefinition: \n");
        if (actionsRecordArray.size() > 0) {
            System.out.println("appsActions.executeFlowAction(\"" + actionsRecordArray.get(0) + "\");\n");
        } else {
            System.out.println("appsActions.searchApp(\"" + getObjectSalesforce() + "\");\n" +
                    "        appsActions.changeViewMode();\n" +
                    "        appsActions.createObject();\n");
        }
        for (int i = 0; i < fieldsArray.size(); i++) {
            System.out.println("appsActions.fillField(\"" + fieldsArray.get(i) + "\", \"" + valuesArray.get(i) + "\");\n");
            if (checkboxArray.size() > i) {
                System.out.println("appsActions.clickCheckbox(\"" + checkboxArray.get(i) + "\");\n");
            }
        }
        System.out.println("\n appsActions.saveObjCreated(); \n");
        if (getTestType().equalsIgnoreCase("negativo")) {
            System.out.println("\n Assert.assertTrue(\"O erro na criação de registro não apareceu!\", appsActions.validateErrorsInRecordCreation()); \n");
        } else {
            System.out.println("\n Assert.assertFalse(\"Não foi possivel salvar o registro por decorrencia de erro no preenchimento\", appsActions.validateErrorsInRecordCreation()); \n");
        }
        System.out.println("#-------------------------------------------------------------------------------------------#");
    }


    @Then("simplifique o teste de validacao")
    public void simplifiqueOTesteDeValidacao() {
        System.out.println("#-------------------------------------------------------------------------------------------#");
        System.out.println("Para criar um Step simplificado dos steps fornecidos de busca e validação de registro, crie um step no gherkin de sua preferencia(exemplo: 'Given que seja validado o objeto conta predefinido') \n e depois no step definition, adicione o codigo abaixo: \n");
        System.out.println("se for fazer em um novo step definition, crie uma instancia fora do metodo da classe de AppActions: \n  AppsActions appsActions = new AppsActions();\n");
        System.out.println("\n Adicione o codigo abaixo no metodo de stepDefinition: \n");
        System.out.println("\n appsActions.searchAndClickRecord(\"" + getObjectSalesforce() + "\"); \n");
        for (int i = 0; i < fieldsArray.size(); i++) {
            System.out.println("appsActions.validateRecordFields(\"" + fieldsArray.get(i) + "\", \"" + valuesArray.get(i) + "\");\n");
        }
        System.out.println("#-------------------------------------------------------------------------------------------#");
    }

    @Given("que a variavel {string} tenha o valor {string}")
    public void queAVariavelTenhaOValor(String varName, String varValue) {
        varNameArray.add(varName);
        varValue = varValue.replace("Random", ApiUtils.getRandomPass(5));
        varValueArray.add(varValue);
    }

    @And("capture o valor do campo {string} e armazene na variavel {string}")
    public void captureOValorDoCampoEArmazeneNaVariavel(String field, String varname) {
        varNameArray.add(varname);
        varValueArray.add(appsActions.createVariablesByFields(field));
    }

    @And("edite o campo {string} com o valor {string}")
    public void editeOCampoComOValor(String field, String value) {
        value = value.replace("Random", ApiUtils.getRandomPass(5));
        appsActions.editFields(field, value);
    }

    @And("edite o campo {string} com o valor da variavel {string}")
    public void editeOCampoComOValorDaVariavel(String field, String varName) {
        String value = varValueArray.get(varNameArray.indexOf(varName));
        appsActions.editFields(field, value);
    }

    @When("Accesar o objeto {string} e mudar o modo de exibicao da lista para {string}")
    public void accesarOObjetoEMudarOModoDeExibicaoDaListaPara(String obj, String list) {
        setObjectSalesforce(obj);
        appsActions.searchApp(obj);
        appsActions.changeViewMode();
        appsActions.switchListView(list);
    }

    @And("clicar no checkbox {string}")
    public void clicarNoCheckbox(String checkboxName) {
        checkboxArray.add(checkboxName);
        appsActions.clickCheckbox(checkboxName);
    }

    @And("preencher o campo {string} com o valor da variavel {string}")
    public void preencherOCampoComOValorDaVariavel(String field, String variable) {
        String value = varValueArray.get(varNameArray.indexOf(variable));
        fieldsArray.add(field);
        valuesArray.add(value);
        appsActions.fillField(field, value);
    }

    @And("clicar na acao de criar um {string}")
    public void clicarNaAcaoDeCriarUm(String action) {
        actionsRecordArray.add(action);
        appsActions.executeFlowAction(action);

    }

    @And("criar um novo caso para a oportunidade")
    public void criarUmNovoCasoParaAOportunidade() {
        appsActions.executeFlowAction("Novo caso");
        appsActions.fillField("Nome do contato", "Andy Young");
        appsActions.fillField("Assunto", "teste de caso");
        appsActions.fillField("Status", "Working");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @And("clicar no botao pelo texto {string}")
    public void clicarNoBotaoPeloTexto(String text) {
        appsActions.clickByText(text);
    }
}
