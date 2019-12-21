package com.datafilter.controller;

import com.datafilter.bean.DataBeanEng;
import com.datafilter.utility.UtilityStatic;
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

    public static ResponseEntity<ArrayList<?>> listOfCountryData = null;
    @Autowired
    RestTemplate restTemplate;

    private int totalData = 1272;
    private final String urlDataGov = "https://data.gov.il/api/action/datastore_search";

    public ResponseEntity<ArrayList<?>> getAllData() throws JsonProcessingException {
        if (listOfCountryData == null) {
            listOfCountryData = getDataFromApi(null);
            return listOfCountryData;
        } else {
            return listOfCountryData;
        }
    }

    public ResponseEntity<ArrayList<?>> getLimitData(Integer limit) throws JsonProcessingException {
        return getDataFromApi(limit);
    }

    public ArrayList<LinkedHashMap> getDataByOriginNumber(int originNumber) throws JsonProcessingException {
        return UtilityStatic.getListByOriginNumber((ArrayList<LinkedHashMap>) getDataFromApi(null).getBody(), originNumber);
    }


    public ArrayList<DataBeanEng> getDataByLang(String lang) throws Exception {
        if (lang.equals("eng")) {
            return UtilityStatic.convertListOfDataBeanFromHebToEng((ArrayList<LinkedHashMap>) getDataFromApi(null).getBody());
        } else {
            throw new Exception("The default language is hebrew can use only english lang=eng");
        }
    }

    private ResponseEntity<ArrayList<?>> getDataFromApi(Integer limit) throws JsonProcessingException {
        ResponseEntity<String> responseData;
        if (limit != null && limit > 0) {
            responseData = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + limit, String.class);
        } else {
            responseData = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab&limit=" + totalData, String.class);
        }
        HashMap<String, Object> result = (HashMap<String, Object>) new ObjectMapper().readValue(responseData.getBody(), HashMap.class).get("result");
        return new ResponseEntity<ArrayList<?>>((ArrayList<?>) result.get("records"), HttpStatus.OK);
    }
}
