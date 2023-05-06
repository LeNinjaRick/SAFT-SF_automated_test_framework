package api;

import okhttp3.*;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ApiUtils {

    public static Response response;

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        ApiUtils.response = response;
    }

    public static String bodyJson;

    public static String getBodyJson() {
        return bodyJson;
    }

    public static void setBodyJson(String bodyJson) {
        ApiUtils.bodyJson = bodyJson;
    }

    public static String url;
    public static String recurso;
    public static String endpoint;

    public static String getAuthorization() {
        return authorization;
    }

    public static void setAuthorization(String authorization) {
        ApiUtils.authorization = authorization;
    }

    public static String authorization;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ApiUtils.url = url;
    }

    public static String getRecurso() {
        return recurso;
    }

    public static void setRecurso(String recurso) {
        ApiUtils.recurso = recurso;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        ApiUtils.endpoint = endpoint;
    }

    //metodo para geração de senha aleatoria, para ser usado na tag externalID do body
    public static String getRandomPass(int len) {
        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++)
            senha[x] = chart[rdm.nextInt(chartLenght)];
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

    public static void requestPOST() {
        bypassTLSCertificateValidation();
        OkHttpClient client = getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, getBodyJson());
        Request request = new Request.Builder()
                .url(getEndpoint())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", getAuthorization())
                .build();
        try {
            setResponse(client.newCall(request).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void requestGET() {
        bypassTLSCertificateValidation();
        OkHttpClient client = getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url(getEndpoint())
                .get()
                .addHeader("Authorization", getAuthorization())
                .addHeader("Accept", "application/json")
                .build();
        try {
            setResponse(client.newCall(request).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validaResponseBodyComRegex(String key, String valueRegex) {
        Assert.assertNotNull(getResponse().body());
        JSONObject jsonObject = new JSONObject(getResponse().body());
        String valueKey = jsonObject.getString(key);
        Assert.assertThat(valueKey, Matchers.equalTo(Pattern.compile("ˆ" + valueRegex + "$")));
    }

    public static void validaResponseBody(String key, String value) {
        Assert.assertNotNull(getResponse().body());
        JSONObject responseJson = new JSONObject(getResponse().body());
        String valueKey = responseJson.getString(key);
        Assert.assertEquals(value, valueKey);
    }

    public static void validaResponseBodyComArray(String keyArray, String key, String value) {
        Assert.assertNotNull(getResponse().body());
        JSONObject responseJson = new JSONObject(getResponse().body());
        JSONArray jArray = responseJson.getJSONArray(keyArray);
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject arrayOBJ = jArray.getJSONObject(i);
            String valueKey = arrayOBJ.getString(key);
            Assert.assertEquals(value, valueKey);
        }
    }

    public static void validaResponseBodyArray(String key, String valueRegex) {
        Assert.assertNotNull(getResponse().body());
        JSONArray responseJson = new JSONArray(getResponse().body());
        for (int i = 0; i < responseJson.length(); i++) {
            JSONObject arrayOBJ = responseJson.getJSONObject(i);
            String valueKey = arrayOBJ.getString(key);
            Assert.assertThat(valueKey, Matchers.equalTo(Pattern.compile("ˆ" + valueRegex + "$")));
        }
    }

    public static void validaResponseBodyComArrayRegex(String keyArray, String key, String valueRegex) {
        Assert.assertNotNull(getResponse().body());
        JSONObject responseJson = new JSONObject(getResponse().body());
        JSONArray jArray = responseJson.getJSONArray(keyArray);
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject arrayOBJ = jArray.getJSONObject(i);
            String valueKey = arrayOBJ.getString(key);
            Assert.assertThat(valueKey, Matchers.equalTo(Pattern.compile("ˆ" + valueRegex + "$")));
        }
    }

    public static void validaResponseCode(int code) {
        System.out.println(getResponse().code());
        Assert.assertEquals("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + getResponse().code() + ";", code, getResponse().code());
    }

    public static void validaResponseHeaderList(List<String> headerKey, List<String> headerValue) {
        for (int i = 0; i < headerKey.size(); i++) {
            String valorHeader = getResponse().header(headerKey.get(i));
            Assert.assertEquals(headerValue.get(i), valorHeader);
        }
    }

    public static void validaResponseHeader(String headerKey, String headerValue) {
        String valorHeader = getResponse().header(headerKey);
        Assert.assertEquals(headerValue, valorHeader);
    }


    public static void bypassTLSCertificateValidation() {
        TrustManager[] trustAllCerts = trustManager();
        SSLContext sc = sslContext(trustAllCerts);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier validHosts = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
    }


    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    static {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        OkHttpClient.Builder okBuilder = new OkHttpClient().newBuilder();
        TrustManager[] trustManager = trustManager();
        SSLContext sslContext = sslContext(trustManager);
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        okBuilder.sslSocketFactory(socketFactory, (X509TrustManager) trustManager[0]);
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        httpClient = okBuilder.build();
    }

    public static OkHttpClient getOhttpClient() {
        return httpClient;
    }


    private static TrustManager[] trustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]
                {new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]
                                {};
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }};
        return trustAllCerts;
    }

    private static final OkHttpClient httpClient;

    private static SSLContext sslContext(TrustManager[] trustAllCerts) {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return sc;
    }


}