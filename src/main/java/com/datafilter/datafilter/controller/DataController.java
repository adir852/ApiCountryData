package com.datafilter.datafilter.controller;

import com.datafilter.datafilter.api.DataApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
public class DataController {

    @Autowired
    RestTemplate restTemplate;

    private int totalData = 1272;
    private final String urlDataGov = "https://data.gov.il/api/action/datastore_search";

    public ResponseEntity<ArrayList<?>> getAllData() throws JsonProcessingException {
        return getDataFromApi(null);
    }

    public ResponseEntity<ArrayList<?>> getLimitData(Integer limit) throws JsonProcessingException {
        return getDataFromApi(limit);
    }

    private ResponseEntity<ArrayList<?>> getDataFromApi(Integer limit) throws JsonProcessingException {
        ResponseEntity<String> responseData;
        if (limit != null && limit > 0) {
            responseData = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + limit, String.class);
        } else {
            responseData = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + totalData, String.class);
        }
        HashMap<String, Object> result = new ObjectMapper().readValue(responseData.getBody(), HashMap.class);
        HashMap<String, Object> res = (HashMap<String, Object>) result.get("result");
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) res.get("records"), HttpStatus.OK);
    }

}
