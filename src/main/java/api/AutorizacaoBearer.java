package api;

import configUtils.ConfigFramework;
import configUtils.PropertiesManager;
import okhttp3.*;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class AutorizacaoBearer extends ConfigFramework {
    private static PropertiesManager pm = new PropertiesManager("src/test/resources/properties/salesforce.properties");
    private static String url = pm.getProps().getProperty("urlSalesForce");
    private static String userPm = pm.getProps().getProperty("username");
    private static String username = pm.getProps().getProperty("usernameAPI");
    private static String passPm = pm.getProps().getProperty("password");
    private static String password = pm.getProps().getProperty("passwordAPI");
    private static String clientId = pm.getProps().getProperty("clientIdAPI");
    private static String redirectUri = pm.getProps().getProperty("redirectURI");

    public static String requestBearer() throws IOException {
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        //troca para proxyServer
       // Proxy proxyServidor = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxytrnhml.enterprisetrn.hdevelo.com.br", 8080));
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                //.proxy(proxyServidor)
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=password&client_id=" + username + "&client_secret=" + password + "&username=" + userPm + "&password=" + passPm + "");
        Request request = new Request.Builder()
                .url("https://test.salesforce.com/services/oauth2/token")
                .method("POST", body)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie", "BrowserId=8EWZTUiWEeysAo968h0Paw; CookieConsentPolicy=0:0; LSKey-c$CookieConsentPolicy=0:0")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
        JSONObject jsonObject = new JSONObject(responseBody);
        String accessCode = jsonObject.getString("access_token");
        if (accessCode.isEmpty()) {
            System.out.println("Erro ao capturar o token OAUTH2");
        }
        System.out.println("bearer OAUTH2: " + accessCode);
        return accessCode;
    }


    @Test
    public void recuperaBearerSalesForce() throws IOException {
        String codigo = requestBearer();
        System.out.println("Codigo gerado: " + codigo);
    }


}