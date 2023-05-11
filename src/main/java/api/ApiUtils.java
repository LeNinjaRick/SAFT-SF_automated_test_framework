package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    private static List<String> actionsArray = new ArrayList<>();

    public static List<String> headersNames = new ArrayList<>();
    public static List<String> headersValues = new ArrayList<>();

    public static List<Header> headersList = new ArrayList<>();


    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        ApiUtils.response = response;
        setResponseBodyJson(response.getBody().prettyPrint());
    }

    public static Response response;

    public static String responseBodyJson;

    public static String getResponseBodyJson() {
        return responseBodyJson;
    }

    public static void setResponseBodyJson(String responseBodyJson) {
        ApiUtils.responseBodyJson = responseBodyJson;
    }

    public static String bodyJson;

    public static String getBodyJson() {
        return bodyJson;
    }

    public static void setBodyJson(String bodyJson) {
        ApiUtils.bodyJson = bodyJson;
    }

    public static String endpoint;

    public static String getAuthorization() {
        return authorization;
    }

    public static void setAuthorization(String authorization) {
        ApiUtils.authorization = authorization;
    }

    public static String authorization;


    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        ApiUtils.endpoint = endpoint;
    }

    public static String getRandomPass(int len) {
        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++) senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static String getRandomString(int len) {
        char[] chart = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++) senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static String getRandomNumber(int len) {
        char[] chart = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++) senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static void criarBody(List<String> bodyKey, List<String> bodyValue) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (int i = 0; i < bodyKey.size(); i++) {
                jsonObject.put(bodyKey.get(i), bodyValue.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setBodyJson(jsonObject.toString());
    }


    public static void createHeaders(String headerName, String headerValue) {
        headersNames.add(headerName);
        headersValues.add(headerValue);
        Header header = new Header(headerName, headerValue);
        headersList.add(header);
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * * * Metodo GET
     * * @return void
     * * @author ricardo.vaz.d.junior
     */
    public static void requestGET() {
        RestAssured.useRelaxedHTTPSValidation();
        Headers header = new Headers(headersList);
        Response response = given().contentType(ContentType.JSON)
                .headers(header)
                .when().get(getEndpoint())
                .then()
                .extract()
                .response();
        setResponse(response);
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * * * Metodo Delete
     * * @return void
     * * @author ricardo.vaz.d.junior
     */
    public static void requestDELETE() {
        RestAssured.useRelaxedHTTPSValidation();
        Headers header = new Headers(headersList);
        Response response = given().contentType(ContentType.JSON)
                .headers(header)
                .when()
                .delete(getEndpoint())
                .then()
                .extract()
                .response();
        setResponse(response);
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given * * Metodo POST * @return void * @author ricardo.vaz.d.junior
     */
    public static void requestPOST() {
        RestAssured.useRelaxedHTTPSValidation();
        Headers header = new Headers(headersList);
        Response response = given().contentType(ContentType.JSON)
                .headers(header)
                .body(getBodyJson())
                .when()
                .post(getEndpoint())
                .then()
                .extract()
                .response();
        setResponse(response);
    }


    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given *
     * * Metodo PUT
     * * @return void
     * * @author ricardo.vaz.d.junior
     */
    public static void requestPUT() {
        RestAssured.useRelaxedHTTPSValidation();
        Headers header = new Headers(headersList);
        Response response = given().contentType(ContentType.JSON)
                .headers(header)
                .body(getBodyJson())
                .when()
                .put(getEndpoint())
                .then()
                .extract()
                .response();
        setResponse(response);
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given *
     * * Metodo PATCH
     * * @return void
     * * @author ricardo.vaz.d.junior
     */
    public static void requestPATCH() {
        RestAssured.useRelaxedHTTPSValidation();
        Headers header = new Headers(headersList);
        Response response = given().contentType(ContentType.JSON)
                .headers(header)
                .body(getBodyJson())
                .when()
                .patch(getEndpoint())
                .then()
                .extract()
                .response();
        setResponse(response);
    }


    public static boolean validaValorInexistenteRA(String valor) {
        assert getResponse() != null;
        return getResponse().body().prettyPrint().contains(valor);
    }

    public static void validaResponseBodyChavesValores(String key1, String value1, String key2, String value2, String key3, String value3) {
        boolean checkValue1 = false;
        boolean checkValue2 = false;
        boolean checkValue3 = false;
        Assert.assertNotNull(getResponse().body());
        JSONArray jsonArray = new JSONArray(getResponseBodyJson());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            String responseElement = jsonArray.get(i).toString();
            if (!(checkValue1 && checkValue2 && checkValue3)) {
                checkValue1 = responseElement.contains("\"" + key1 + "\":\"" + value1 + "\"") || responseElement.contains("\"" + key1 + "\":" + value1 + "");
                checkValue2 = responseElement.contains("\"" + key2 + "\":\"" + value2 + "\"") || responseElement.contains("\"" + key2 + "\":" + value2 + "") && checkValue1;
                checkValue3 = responseElement.contains("\"" + key3 + "\":\"" + value3 + "\"") || responseElement.contains("\"" + key3 + "\":" + value3 + "") && checkValue3;
            }
        }
        Assert.assertTrue("1º valor diferente do esperado!", checkValue1);
        Assert.assertTrue("2º valor diferente do esperado!", checkValue2);
        Assert.assertTrue("3º valor diferente do esperado!", checkValue3);
    }


    /**
     * Realiza a validação de campos especificos do body response, atraves do mapeamento json e os compara com Regex
     * *
     * * Só realiza a validação de responses do tipo Rest-Assured
     * *
     * * @return void
     * * @author ricardo.vaz.d.junior
     */
    public static void validaResponseCode(String code) {
        String statuscode = String.valueOf(getResponse().getStatusCode());
        System.out.println("code Rest assured: " + statuscode);
        Assert.assertEquals("O Response code foi diferente do esperado!", code, statuscode);
    }

    public static void validaResponseHeader(String headerKey, String headerValue) {
        String valorHeader = getResponse().header(headerKey);
        Assert.assertEquals(headerValue, valorHeader);
    }

    /**
     * Realiza a validação de campos especificos do body response, atraves do mapeamento json e os compara com Regex * * Só realiza a validação de responses do tipo Rest-Assured* * * @param key - objeto a ser procurado - exemplo: data.email, ou em caso de arrays: data.leads[0].email * @param value - Regex a ser usado na validacao, sempre usando '^$', exemplo: ^.*$, ^\d*$ * @return void * @author ricardo.vaz.d.junior
     */
    public static void validaResponseBodyComRegex(String key, String value) {
        String data = getResponse().jsonPath().get(key).toString();
        Assert.assertThat(data, RegexMatcher.matchesRegex(value));
    }

}