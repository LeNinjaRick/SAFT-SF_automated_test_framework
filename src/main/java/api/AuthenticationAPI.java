package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import configUtils.PropertiesManager;
import okhttp3.*;

import java.io.IOException;

public class AuthenticationAPI {
    public static String getSalesforceAccessToken() throws IOException {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String SALESFORCE_URL = pm.getProps().getProperty("SALESFORCE_URL");
        String SALESFORCE_USERNAME = pm.getProps().getProperty("SALESFORCE_USERNAME");
        String SALESFORCE_PASSWORD = pm.getProps().getProperty("SALESFORCE_PASSWORD");
        String SALESFORCE_GRANT_TYPE = pm.getProps().getProperty("SALESFORCE_GRANT_TYPE");
        String SALESFORCE_CLIENT_ID = pm.getProps().getProperty("SALESFORCE_CLIENT_ID");
        String SALESFORCE_CLIENT_SECRET = pm.getProps().getProperty("SALESFORCE_CLIENT_SECRET");

        StringBuilder dataParams = new StringBuilder();
        dataParams.append("grant_type=" + SALESFORCE_GRANT_TYPE + "&");
        dataParams.append("client_id=" + SALESFORCE_CLIENT_ID + "&");
        dataParams.append("client_secret=" + SALESFORCE_CLIENT_SECRET + "&");
        dataParams.append("username=" + SALESFORCE_USERNAME + "&");
        dataParams.append("password=" + SALESFORCE_PASSWORD);
        ApiUtils.bypassTLSCertificateValidation();
        OkHttpClient client;
        client = ApiUtils.getOhttpClient();
        client.newBuilder().build();
        MediaType mediaType = MediaType.parse("application/none");
        RequestBody body = RequestBody
                .create(mediaType, "");
        Request request = new Request.Builder()
                .url(SALESFORCE_URL + "/services/oauth2/token?" + dataParams.toString())
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);
        JsonObject jsonResponseBody = new Gson().fromJson(response.body().string(), JsonObject.class);
        String accessToken = jsonResponseBody.get("access_token").getAsString();
        return accessToken;
    }
}
