package com.datafilter.datafilter.controller;

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

    public ResponseEntity<ArrayList<?>> getAllData() throws JsonProcessingException {
        boolean isNotlastPage = true;
        ResponseEntity<String> data;
        int offset = 0;
        int total = 0;
        ArrayList<Object> arrayList = new ArrayList<>();
        while (isNotlastPage) {
            data = restTemplate.getForEntity("https://data.gov.il/api/action/datastore_search?offset=" + offset + "&resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab", String.class);
            HashMap<String, Object> result = new ObjectMapper().readValue(data.getBody(), HashMap.class);
            LinkedHashMap<String, Object> res = (LinkedHashMap<String, Object>) result.get("result");
            arrayList.add(res.get("records"));
            offset += 100;
            total = (int) res.get("total");
            if (offset >= total) {
                isNotlastPage = false;
            } else if (data.getStatusCode() != HttpStatus.OK) {
                isNotlastPage = false;
            }
        }
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) arrayList, HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<?>> getLimitData(int limit) throws JsonProcessingException {
        ResponseEntity<String> data = restTemplate.getForEntity("https://data.gov.il/api/action/datastore_search?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + limit, String.class);
        HashMap<String, Object> result = new ObjectMapper().readValue(data.getBody(), HashMap.class);
        LinkedHashMap<String, Object> res = (LinkedHashMap<String, Object>) result.get("result");
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) res.get("records"), HttpStatus.OK);
    }


}
