package stepDefinitions;

import api.ApiUtils;
import api.AuthenticationAPI;
import configUtils.PropertiesManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class APIStepDefinition {

    @When("^executar uma requisicao POST$")
    public void executarUmaRequisicaoPOST() {
        ApiUtils.requestPOST();
    }
    @When("^executar uma requisicao GET$")
    public void executarUmaRequisicaoGET() {
        ApiUtils.requestGET();
    }
    @Then("^espero receber um response code \"([^\"]*)\"$")
    public void esperoReceberUmResponseCode(String code) {
        ApiUtils.validaResponseCode(Integer.parseInt(code));
    }

    @Given("^que seja definido a autorizacao oauth do salesForce$")
    public void queSejaDefinidoAAutorizacaoOauthDoSalesForce() {
        try {
            ApiUtils.setAuthorization("Bearer " + AuthenticationAPI.getSalesforceAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Given("^que seja definido o payload \"([^\"]*)\"$")
    public void queSejaDefinidoOPayload(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payload = pm.getProps().getProperty(arg0);
        ApiUtils.setBodyJson(payload);
    }
    @Then("^espero que o objeto do body \"([^\"]*)\" venha com o valor de \"([^\"]*)\"$")
    public void esperoQueOObjetoDoBodyVenhaComOValorDe(String key, String value) {
        ApiUtils.validaResponseBody(key, value);
    }
    @Then("^espero que o objeto do body \"([^\"]*)\" venha com a estrutura do regex \"([^\"]*)\"$")
    public void esperoQueOObjetoDoBodyVenhaComAEstruturaDoRegex(String key, String valueRegex) {
        ApiUtils.validaResponseBodyComRegex(key, valueRegex);
    }
    @Then("^espero que o body response array, objeto \"([^\"]*)\" venha com o valor \"([^\"]*)\"$")
    public void esperoQueOBodyResponseArrayObjetoVenhaComOValor(String key, String value) {
        ApiUtils.validaResponseBodyArray(key, value);
    }
    @Given("^que seja definido o endpoint como \"([^\"]*)\"$")
    public void queSejaDefinidoOEndpointComo(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String endpoint = pm.getProps().getProperty(arg0);
        ApiUtils.setEndpoint(endpoint);
    }
    @Given("^que seja definido o payload \"([^\"]*)\", aleatorizando os valores \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void queSejaDefinidoOPayloadAleatorizandoOsValoresE(String payload, String value1, String value2, String value3) throws Throwable {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payloadJson = pm.getProps().getProperty(payload);
        payloadJson = payloadJson.replaceAll(value1,ApiUtils.getRandomPass(6));
        if(!value2.isEmpty()){
            payloadJson = payloadJson.replaceAll(value2,ApiUtils.getRandomPass(6));
        }
        if(!value3.isEmpty()){
            payloadJson = payloadJson.replaceAll(value3,ApiUtils.getRandomPass(6));
        }
        ApiUtils.setBodyJson(payloadJson);
    }

}
