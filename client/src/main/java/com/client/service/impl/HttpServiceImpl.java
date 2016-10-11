package com.client.service.impl;

import com.client.service.HttpService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by z00382545 on 10/11/16.
 */

@Service
public class HttpServiceImpl implements HttpService{

    public void getResource() throws IOException, JSONException {
        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://localhost:8080/auth/realms/demo/protocol/openid-connect/token");
//        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("client_id", "admin-cli"));
//        params.add(new BasicNameValuePair("client_secret", "2a05c119-6254-481a-9b0d-a3f6993c85cc"));
//
//        params.add(new BasicNameValuePair("username", "ldeng"));
//        params.add(new BasicNameValuePair("password", "password"));
//        params.add(new BasicNameValuePair("grant_type", "password"));

//        httpPost.setEntity(new UrlEncodedFormEntity(params));

//        CloseableHttpResponse response = client.execute(httpPost);

        HttpGet httpGet = new HttpGet("http://localhost:8082/resource/patient/all");
        CloseableHttpResponse response = client.execute(httpGet);

        System.out.println(response.getEntity().getContent());

        JsonNode n = responseParser(response);
    }


    public JsonNode responseParser(CloseableHttpResponse response) throws IOException, JSONException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            result.append(line);
        }

        System.out.println(result.toString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(result.toString());

        return actualObj;
    }
}
