package com.datafilter.datafilter.controller;

import com.datafilter.datafilter.api.DataApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

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
        ResponseEntity<String> data = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + totalData, String.class);
        HashMap<String, Object> result = new ObjectMapper().readValue(data.getBody(), HashMap.class);
        LinkedHashMap<String, Object> res = (LinkedHashMap<String, Object>) result.get("result");
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) res.get("records"), HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<?>> getLimitData(int limit) throws JsonProcessingException {
        ResponseEntity<String> data = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + limit, String.class);
        HashMap<String, Object> result = new ObjectMapper().readValue(data.getBody(), HashMap.class);
        LinkedHashMap<String, Object> res = (LinkedHashMap<String, Object>) result.get("result");
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) res.get("records"), HttpStatus.OK);
    }


}
