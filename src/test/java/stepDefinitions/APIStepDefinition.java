package stepDefinitions;

import api.ApiUtils;
import configUtils.PropertiesManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static api.ApiUtils.*;
import static stepDefinitions.WebStepDefinition.*;

public class APIStepDefinition {

    private static List<String> jsonPathArray = new ArrayList<String>();
    static StringBuilder dataParams = new StringBuilder();

    private static List<String> keyNames = new ArrayList<String>();
    private static List<String> keyValues = new ArrayList<String>();
    public static List<String> varApiNameArray = new ArrayList<String>();

    public static List<String> varApiValuesArray = new ArrayList<String>();

    public static String getRequestType() {
        return requestType;
    }

    public static void setRequestType(String requestType) {
        APIStepDefinition.requestType = requestType;
    }

    private static String requestType;
    private static String expectedCode;

    public static String getExpectedCode() {
        return expectedCode;
    }

    public static void setExpectedCode(String expectedCode) {
        APIStepDefinition.expectedCode = expectedCode;
    }

    @When("^executar uma requisicao POST$")
    public void executarUmaRequisicaoPOST() {
        ApiUtils.requestPOST();
        setRequestType("POST");
    }

    @When("^executar uma requisicao DELETE$")
    public void executarUmaRequisicaoDELETE() {
        ApiUtils.requestDELETE();
        setRequestType("DELETE");
    }


    @When("^executar uma requisicao PUT$")
    public void executarUmaRequisicaoPUT() {
        ApiUtils.requestPUT();
        setRequestType("PUT");
    }


    @When("^executar uma requisicao GET$")
    public void executarUmaRequisicaoGET() {
        ApiUtils.requestGET();
        setRequestType("GET");
    }


    @When("^executar uma requisicao PATCH$")
    public void executarUmaRequisicaoPATCH() {
        ApiUtils.requestPATCH();
        setRequestType("PATCH");
    }

    @Then("^espero receber um response code \"([^\"]*)\"$")
    public void esperoReceberUmResponseCode(String code) {
        setExpectedCode(code);
        ApiUtils.validaResponseCode(code);
    }

    @Given("^que seja definido o payload \"([^\"]*)\"$")
    public void queSejaDefinidoOPayload(String payload) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payloadTratado = pm.getProps().getProperty(payload);
        payloadTratado = payloadTratado.replace("RandomNum", ApiUtils.getRandomNumber(3));
        payloadTratado = payloadTratado.replace("RandomPass", ApiUtils.getRandomPass(5));
        payloadTratado = payloadTratado.replace("RandomString", ApiUtils.getRandomString(5));

        ApiUtils.setBodyJson(payloadTratado);
    }

    @Given("^que seja definido o endpoint como \"([^\"]*)\"$")
    public void queSejaDefinidoOEndpointComo(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String endpoint = pm.getProps().getProperty(arg0);
        ApiUtils.setEndpoint(endpoint);
    }

    @Given("^que seja definido o payload \"([^\"]*)\", modificando os valores \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\" para \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void queSejaDefinidoOPayloadModificandoOsValoresEPara(String payload, String campo1, String campo2, String campo3, String campo4, String valor1, String valor2, String valor3, String valor4) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payloadJson = pm.getProps().getProperty(payload); // identificando o payload em formato string (hunter)
        JSONObject payloadFormatted = new JSONObject(payloadJson); // estanciando o objeto json para parametrizar o valor
        payloadFormatted.put(campo1, valor1); // parametrizando os valores
        payloadFormatted.put(campo2, valor2); // parametrizando os valores
        payloadFormatted.put(campo3, valor3); // parametrizando os valores
        payloadFormatted.put(campo4, valor4); // campo adicionado (caso não exista esse campo no payload, então ele irá adicionar! caso exista, irá mudar valor
        ApiUtils.setBodyJson(String.valueOf(payloadFormatted));
    }

    @Given("^que seja adicionado os parametros \"([^\"]*)\" no endpoint \"([^\"]*)\"$")
    public void queSejaAdicionadoOsParametrosNoEndpoint(String param, String endpoint) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/acl.properties");
        String uri = pm.getProps().getProperty(endpoint);
        uri = (uri + param);
        ApiUtils.setEndpoint(uri);
    }

    @Then("^espero que a body response possua \"([^\"]*)\" \"([^\"]*)\", \"([^\"]*)\" \"([^\"]*)\" e \"([^\"]*)\" \"([^\"]*)\"$")
    public void esperoQueABodyResponsePossuaE(String key1, String value1, String key2, String value2, String key3, String value3) {
        ApiUtils.validaResponseBodyChavesValores(key1, value1, key2, value2, key3, value3);
    }

    @Then("^espero que nao exista o valor \"([^\"]*)\" no body response$")
    public void esperoQueNaoExistaOValorNoBodyResponse(String arg0) {
        Assert.assertFalse("O valor procurado existe no body response!", ApiUtils.validaValorInexistenteRA(arg0));
    }

    @And("armazene o valor do campo de response body {string} na variavel {string}")
    public void armazeneOValorDoCampoDeResponseBodyNaVariavel(String jsonPath, String varName) {
        String data = getResponse().jsonPath().get(jsonPath).toString();
        jsonPathArray.add(jsonPath);
        varApiNameArray.add(varName);
        varApiValuesArray.add(data);
    }

    @And("espero que o campo {string} do response body esteja com o valor de {string}")
    public void esperoQueOCampoDoResponseBodyEstejaComOValorDe(String key, String valueRegex) {
        keyNames.add(key);
        keyValues.add(valueRegex);
        ApiUtils.validaResponseBodyComRegex(key, valueRegex);
    }

    @Given("que seja definido o header {string} com o valor {string}")
    public void queSejaDefinidoOHeaderComOValor(String headerName, String headerValue) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        headersNames.add(headerName);
        headersValues.add(headerValue);
        String value = headerValue;
        if (varNameArray.contains(headerValue)) {
            value = varValueArray.get(varNameArray.indexOf(headerValue));
            ApiUtils.createHeaders(headerName, value);
        } else if (varApiNameArray.contains(headerValue)) {
            value = varApiValuesArray.get(varApiNameArray.indexOf(headerValue));
            ApiUtils.createHeaders(headerName, value);
        } else if (pm.getProps().containsKey(headerValue)) {
            value = pm.getProps().getProperty(headerValue);
            ApiUtils.createHeaders(headerName, value);
        } else {
            ApiUtils.createHeaders(headerName, headerValue);
        }
    }

    @And("simplifique o teste de API")
    public void simplifiqueOTesteDeAPI() {
        System.out.println("#-------------------------------------------------------------------------------------------#");
        System.out.println("Para criar um Step simplificado dos steps fornecidos de busca e validação de registro, \n crie um step no gherkin de sua preferencia(exemplo: 'Given que seja feito o teste de API de geracao de protocolo') \n e depois no step definition, adicione o codigo abaixo: \n");
        System.out.println("\n *** Adicione o codigo abaixo no metodo de stepDefinition: *** \n");
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        for (int i = 0; i < headersNames.size(); i++) {
            String value = headersValues.get(i);
            if (varNameArray.contains(headersValues.get(i))) {
                System.out.println(" String value = varValueArray.get(varNameArray.indexOf(\"" + headersValues.get(i) + "\"));\n" +
                        "                ApiUtils.createHeaders(\"" + headersNames.get(i) + "\", value);\n");
            } else if (varApiNameArray.contains(headersValues.get(i))) {
                System.out.println(" String value = varApiValuesArray.get(varApiNameArray.indexOf(\"" + headersValues.get(i) + "\"));\n" +
                        "                ApiUtils.createHeaders(\"" + headersNames.get(i) + "\", value);\n");
            } else if (pm.getProps().containsKey(headersValues.get(i))) {
                System.out.println("String value = pm.getProps().getProperty(\"" + headersValues.get(i) + "\");\n" +
                        "                ApiUtils.createHeaders(\"" + headersNames.get(i) + "\", value);\n");
            } else {
                System.out.println("ApiUtils.createHeaders(\"" + headersNames.get(i) + "\", \"" + headersValues.get(i) + "\");");
            }
        }
        if (getRequestType().equalsIgnoreCase("GET") || getRequestType().equalsIgnoreCase("DELETE")) {
            System.out.println(" RestAssured.useRelaxedHTTPSValidation();\n" +
                    "        Headers header = new Headers(ApiUtils.headersList);\n" +
                    "        Response response = given()\n" +
                    "                .headers(header)\n" +
                    "                .when()." + getRequestType().toLowerCase() + "(\"" + getEndpoint() + "\")\n" +
                    "                .then()\n" +
                    "                .extract()\n" +
                    "                .response();\n" +
                    "        ApiUtils.setResponse(response);");
        } else {
            System.out.println(" RestAssured.useRelaxedHTTPSValidation();\n" +
                    "        Headers header = new Headers(ApiUtils.headersList);\n" +
                    "        Response response = given()\n" +
                    "                .headers(header)\n" +
                    "                .body(\"" + getBodyJson() + "\")\n" +
                    "                .when()." + getRequestType().toLowerCase() + "(\"" + getEndpoint() + "\")\n" +
                    "                .then()\n" +
                    "                .extract()\n" +
                    "                .response();\n" +
                    "        ApiUtils.setResponse(response);");

        }

        if (!getExpectedCode().isEmpty()) {
            System.out.println("\n ApiUtils.validaResponseCode(\"" + getExpectedCode() + "\");\n");
        }
        for (int i = 0; i < keyNames.size(); i++) {
            System.out.println("Codigo para validar os campos do response body: \n");
            System.out.println("ApiUtils.validaResponseBodyComRegex(\"" + keyNames.get(i) + "\", \"" + keyValues.get(i) + "\");\n");
        }
        for (int i = 0; i < varApiNameArray.size(); i++) {
            System.out.println("Codigo para guardar variaveis no contexto de teste WEB: \n");
            System.out.println("**Se a variavel não tiver valor, não tem necessidade de cria-la em seu codigo**\n");
            if (jsonPathArray.size() > i) {
                System.out.println("String data = getResponse().jsonPath().get(\"" + jsonPathArray.get(i) + "\").toString();\n");
            }
            System.out.println("varApiNameArray.add(\"" + varApiNameArray.get(i) + "\");\n");
            System.out.println("varApiValuesArray.add(data);\n");
        }

        System.out.println("#-------------------------------------------------------------------------------------------#");
    }

    @And("que seja definido o body parametrizavel com a tag {string} e valor {string}")
    public void queSejaDefinidoOBodyParametrizavelComATagEValor(String tag, String value) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        if (dataParams.length() > 0 && pm.getProps().containsKey(value)) {
            dataParams.append("&" + tag + "=" + pm.getProps().getProperty(value));
        } else if (dataParams.length() == 0 && pm.getProps().containsKey(value)) {
            dataParams.append(tag + "=" + pm.getProps().getProperty(value));
        } else if (dataParams.length() == 0) {
            dataParams.append(tag + "=" + value);
        } else {
            dataParams.append("&" + tag + "=" + value);
        }
        setBodyJson(dataParams.toString());
    }
}
