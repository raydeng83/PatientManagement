package com.client.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import com.client.domain.Patient;

/**
 * Created by z00382545 on 10/11/16.
 */
public interface HttpService {
    JSONObject getToken(String authCode) throws IOException, JSONException;

    List<Patient> getResource(String token) throws IOException, JSONException;

    List<Patient> responseParser(CloseableHttpResponse response) throws IOException, JSONException;

    String getPublicInfo() throws IOException;
}
