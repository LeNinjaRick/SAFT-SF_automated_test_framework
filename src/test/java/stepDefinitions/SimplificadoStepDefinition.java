package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import web.AppsActions;

public class SimplificadoStepDefinition {


    //Todo o codigo dessa classe é dedicado a exemplificar o uso de simplicação de codigo;

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




    @And("criar oportunidade")
    public void criarOportunidade() {
        appsActions.clickCheckbox("Particular");
        appsActions.fillField("Valor", "123");
        appsActions.fillField("Nome da oportunidade", "teste oportunidade Random ");
        appsActions.fillField("Próxima etapa", "teste");
        appsActions.fillField("Order Number", "12344");
        appsActions.fillField("Main Competitor(s)", "teste");
        appsActions.fillField("Probabilidade (%)", "20");
        appsActions.fillField("Current Generator(s)", "teste");
        appsActions.fillField("Tracking Number", "11111");
        appsActions.fillField("Nome da conta", "testeConta Bdyxj");
        appsActions.fillField("Origem da campanha principal", "DM Campaign to Top Customers - Nov 12-23, 2001");
        appsActions.fillField("Data de fechamento", "12/12/2000");
        appsActions.fillField("Fase", "Prospecting");
        appsActions.fillField("Tipo", "testeConta Bdyxj");
        appsActions.fillField("Origem do lead", "Web");
        appsActions.fillField("Delivery/Installation Status", "Completed");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }

    @And("crie o registro com os dados retornados de mapeamento")
    public void crieORegistroComOsDadosRetornadosDeMapeamento() {

        //Codigo gerado pelo metodo de auto Mapeamento, apenas colocando valores aos campos retornados
        appsActions.fillField("Telefone", "123456789");
        appsActions.fillField("Primeiro Nome", "ricardo Random");
        appsActions.fillField("Sobrenome", "Random");
        appsActions.fillField("Telefone residencial", "1998767898");
        appsActions.fillField("Celular", "19976789890");
        appsActions.fillField("Cargo", "testes");
        appsActions.fillField("Outro telefone", "19987898767");
        appsActions.fillField("Departamento", "TI");
        appsActions.fillField("Fax", "teste");
        appsActions.fillField("Data de nascimento", "12/12/2000");
        appsActions.fillField("Email", "ricardo@email.com");
        appsActions.fillField("Assistente", "joelma");
        appsActions.fillField("Telefone do assistente", "1298787878");
        appsActions.fillField("Rua de correspondência", "rua teste");
        appsActions.fillField("Cidade de correspondência", "Campinas");
        appsActions.fillField("Estado/Província de correspondência", "São Paulo");
        appsActions.fillField("CEP de correspondência", "12123432");
        appsActions.fillField("País de correspondência", "Brasil");
        appsActions.fillField("Outra rua", "rua teste 2");
        appsActions.fillField("Outra cidade", "Campinas 2");
        appsActions.fillField("Outro estado/província", "São Paulo");
        appsActions.fillField("Outro CEP", "12123435");
        appsActions.fillField("Outro país", "Brasil");
        appsActions.fillField("Languages", "ingles e espanhol");
        appsActions.fillField("Descrição", "teste 2");
        appsActions.fillField("Tratamento", "Dr.");
        appsActions.fillField("Nome da conta", "Postman");
        appsActions.fillField("Reporta-se a", "Andy Young");
        appsActions.fillField("Origem do lead", "Web");
        appsActions.fillField("Level", "Primary");
        appsActions.saveObjCreated();
        Assert.assertFalse("Não foi possivel salvar o registro por decorrencia de erro no preenchimento", appsActions.validateErrorsInRecordCreation());
    }
}
