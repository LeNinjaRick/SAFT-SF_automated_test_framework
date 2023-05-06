package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import web.ActionUtils;

import static configUtils.ConfigFramework.getBrowser;

public class SetupPO {

    //Logar no SalesForce:
    public static By username = By.id("username");
    public static By password = By.id("password");
    public static By btnLoginSF = By.id("Login");
    //Tela inicial:
    // valida a organização:
    public static By loadingSandBox = By.xpath("//*[@id=\"auraLoadingBox\"]");
    public static By messageSandBox = By.xpath("//*[@id=\"oneHeader\"]//span[normalize-space(.)='Sandbox: TIACC']");
    // botão app launcher
    public static By appLauncher = By.xpath("//one-app-launcher-header/button");
    // input de pesquisa de APPs:
    public static By searchApp = By.xpath("//input[@placeholder='Search apps and items...']");
    // input de pesquisa de registros:
    public static By btnSearchRegistros = By.xpath("//button[@aria-label='Search']");
    public static By inputSearchRegistros = By.xpath("//input[@placeholder='Search...']");

    //resultado de registros
    public static By resultadoBuscaRegistro(String registro) {
        return By.xpath("//span[contains(@title, '" + registro + "')]");
    }

    public static By labelsConfig(String label) {
        return By.xpath("//*[contains(text(), '" + label + "')]");
    }

    // resultado da pesquisa de APP:
    public static By resultadoBuscaAPP(String app) {
        return By.xpath("//a[contains(@data-label, '" + app + "')]");
    }

    public static Boolean buscaAPPeClica(String app) {
        try {
            ActionUtils.fillInput(getBrowser(), searchApp, app, 10);
            ActionUtils.click(getBrowser(), resultadoBuscaAPP(app), 10);
            return true;
        } catch (Exception e) {
            Assert.fail("Não foi possivel encontrar o APP escolhido. " + e.getMessage());
            return false;
        }
    }

    public static By fieldService = By.xpath("//a[@data-label='Field Service']");
    //entrar no setup:
    public static By btnEngrenagem = By.xpath("//a[contains(@class, 'menuTriggerLink')]");
    public static By btnSetup = By.xpath("//li[@id='related_setup_app_home']/a");
    //input de pesquisa:
    public static By inputSearchSetup = By.xpath("//input[@placeholder='Search Setup']");

    // Adquire os resultados da pesquisa feita no input acima
    public static By resultadoBusca(String usuario) {
        return By.xpath("//a[@role='option']//descendant::span[@title='" + usuario + "']");
        // ou
        //return By.xpath("//span[@title='"+nomeObjeto+"']//ancestor::a[@role='option']");
    }

    //Mudar Login
    public static By iframe_perfis = By.xpath("//iframe");
    public static By btnLoginUser = By.xpath("//td[@id='topButtonRow']//input[@name='login']");
    //Validar Login
    public static By labelLoggedAs = By.xpath("//div[@data-message-id='loginAsSystemMessage']/span[normalize-space(.)='Logged in as Ricardo Suporte Cielo (ricardo.suporte.cielo@accenture.com.cielo.tiacc) | Sandbox: TIACC |']");
    //Validar app reagendamento e clicar em tudo
    public static By labelItemReagendamento = By.xpath("//div[contains(@class, 'slds-breadcrumb__item slds-line-height--reset')]//span[contains(text(), 'Reagendamentos')]");
    public static By btnVistosRecentes = By.xpath("//div[@class ='slds-page-header__name-title']//span[text()='Recently Viewed']");
    public static By btnTudo = By.xpath("//span[text()='Tudo']");
    //input para pesquisar o motivo
    public static By inputPesquisarMotivo = By.xpath("//input[@placeholder='Search this list...']");
    //clica na foto de perfil:
    //pega o nome do usuario, tambem é clicavel
    public static By labelUser = By.xpath("//h1/a[contains(@class, 'profile-link-label')]");
    // fazer loggout do usuario
    public static By btnLogout = By.xpath("//a[contains(@class, 'logout')]");
    public static By btnLogoutAs = By.xpath("//a[contains(normalize-space(.), 'Log out as')]");
    //objetos do developer console:
    public static By menuDebug = By.xpath("//*[@id='debugMenuEntry-btnEl']");
    public static By openApexCommandLine = By.xpath("//*[@id='openExecuteAnonymousWindow-itemEl']");
    // public static By textAreaCommand = By.xpath("//*[@id=\"ux-codemirror-1176-inputEl\"]");
    public static By textAreaCommand = By.xpath("//*[@id='panel-1175-body']//descendant::textarea[@tabindex='0']");
    public static By btnExecuteCommand = By.xpath("//span[text()='Execute']//parent::button");
    public static By logSucesso = By.xpath("//div[text()='Success']");
    public static By msgCommandLineAberto = By.xpath("//*[text()='Enter Apex Code']");
    public static By botaoExibirLog = By.xpath("//*[@id='openLogAfterExec-inputEl']");
    public static By elementPRE = By.xpath("//*[@id='panel-1175-body']//div[contains(@class, 'code')]//pre");
    public static By textoPreenchido = By.xpath("//span[contains(text(),'SELECT')]");

    public static By labelCamposSetup(String nomecampo) {
        return By.xpath("//*[contains(text(),'" + nomecampo + "')]");
    }

    public static By falhaLoginEnded = By.xpath("//lightning-formatted-text[contains(text(),'Your session has ended')]");
    public static By falhaLogin = By.xpath("//div[contains(text(),'Check your Internet connection')]");
    //seleciona o motivo
    public static By selecionarMotivo = By.xpath("//a[@title = 'Cancelado por logística']");
    //checa se está na tela do motivo correto
    public static By labelTelaMotivo = By.xpath("//slot[contains(@class, 'slds-page-header__title slds-m-right--small slds-align-middle clip-text slds-line-clamp')]//lightning-formatted-text[text()='Cancelado por logística']");
    //lapis do pickçist de envia ordem de serviço
    public static By picklistEnviaOrdemDeServico = By.xpath("//span[text()='Edit Envia Ordem de Serviço']");
    //clica na checkbox de envia ordem de servico
    public static By checkboxEnviaOrdemDeServico = By.xpath("//label/span[text()='Envia Ordem de Serviço']");
    //verifica e clica no botão salvar
    public static By btnSalvar = By.xpath("//button[@name = 'SaveEdit']");
}
