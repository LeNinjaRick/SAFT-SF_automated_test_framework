package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import web.AppsActions;

public class SimplificadoStepDefinition {

    AppsActions appsActions = new AppsActions();
    @Given("criar uma conta de massa de testes")
    public void criarUmaContaDeMassaDeTestes() {
        appsActions.searchApp("Contas");
        appsActions.changeViewMode();
        appsActions.createObject();
        appsActions.fillField("Nome da conta", "testeConta 59EKG");
        appsActions.fillField("Classificação", "Hot");
        appsActions.fillField("Conta pai", "Postman");
        appsActions.fillField("SLA", "Gold");
        appsActions.fillField("SLA Serial Number", "12340BxTJ");

        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @When("valida a conta do Andy")
    public void validaAContaDoAndy() {
        appsActions.searchAndClickRecord("Andy Young");
        appsActions.validateRecordFields("Nome completo", "Mr Andy Young");
        appsActions.validateRecordFields("Celular", "^(.*)$");
        appsActions.validateRecordFields("Level", "Primary");
    }

    @When("criar uma oportunidade em progresso")
    public void criarUmaOportunidadeEmProgresso() {
        appsActions.searchApp("Oportunidades");
        appsActions.changeViewMode();
        appsActions.createObject();
        appsActions.fillField("Nome da oportunidade", "oportunidade Random");
        appsActions.clickCheckbox("Particular");
        appsActions.fillField("Data de fechamento", "27/04/2023");
        appsActions.fillField("Fase", "Qualification");
        appsActions.fillField("Nome da conta", "testeConta Bdyxj");
        appsActions.fillField("Delivery/Installation Status", "In progress");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @Given("que seja criado o objeto conta predefinido")
    public void queSejaCriadoOObjetoContaPredefinido() {
        appsActions.searchApp("Contas");
        appsActions.changeViewMode();
        appsActions.createObject();
        appsActions.fillField("Nome da conta", "testeConta Random");
        appsActions.fillField("Classificação", "Hot");
        appsActions.fillField("Conta pai", "Postman");
        appsActions.fillField("SLA", "Gold");
        appsActions.fillField("SLA Serial Number", "1234Random");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @When("criar uma massa de oportunidade")
    public void criarUmaMassaDeOportunidade() {

        appsActions.searchApp("Oportunidades");
        appsActions.changeViewMode();
        appsActions.createObject();
        appsActions.fillField("Nome da oportunidade", "oportunidade T1EXP");
        appsActions.clickCheckbox("Particular");
        appsActions.fillField("Data de fechamento", "27/04/2023");
        appsActions.fillField("Fase", "Qualification");
        appsActions.fillField("Nome da conta", "testeConta Bdyxj");
        appsActions.fillField("Delivery/Installation Status", "In progress");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
        appsActions.executeFlowAction("Novo caso");
        appsActions.fillField("Nome do contato", "Andy Young");
        appsActions.fillField("Assunto", "teste de caso");
        appsActions.fillField("Status", "Working");
        appsActions.saveObjCreated();

        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }


    @And("teste teste")
    public void testeTeste() {

        appsActions.searchApp("Oportunidades");
        appsActions.changeViewMode();
        appsActions.createObject();

        appsActions.fillField("Nome da oportunidade", "oportunidade XbDPg");

        appsActions.clickCheckbox("Particular");

        appsActions.fillField("Data de fechamento", "27/04/2023");

        appsActions.fillField("Fase", "Qualification");

        appsActions.fillField("Nome da conta", "testeConta Bdyxj");

        appsActions.fillField("Delivery/Installation Status", "In progress");


        appsActions.saveObjCreated();


        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @And("criar oportunidade")
    public void criarOportunidade() {
        appsActions.fillField("Valor", "valorCampo");
        appsActions.fillField("*Nome da oportunidade", "valorCampo");
        appsActions.fillField("Próxima etapa", "valorCampo");
        appsActions.fillField("Probabilidade (%)", "valorCampo");
        appsActions.fillField("Order Number", "valorCampo");
        appsActions.fillField("Main Competitor(s)", "valorCampo");
        appsActions.fillField("Current Generator(s)", "valorCampo");
        appsActions.fillField("Tracking Number", "valorCampo");
        appsActions.fillField("Nome da conta", "valorCampo");
        appsActions.fillField("Origem da campanha principal", "valorCampo");
        appsActions.fillField("Descrição", "valorCampo");
        appsActions.fillField("*Data de fechamento", "valorCampo");
        appsActions.fillField("*Fase", "Prospecting");
        appsActions.fillField("Tipo", "valorCampo");
        appsActions.fillField("Origem do lead", "valorCampo");
        appsActions.fillField("Delivery/Installation Status", "valorCampo");
        appsActions.clickCheckbox("Particular");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }
}
