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

@Controller
public class DataController {

    public static ResponseEntity<ArrayList<?>> listOfCountryData = null;

    @Autowired
    RestTemplate restTemplate;

    public static int totalData = 0;
    private final String urlDataGov = "https://data.gov.il/api/action/datastore_search";

    public ResponseEntity<ArrayList<?>> getAllDataCache() throws JsonProcessingException {
        if (listOfCountryData == null) {
            listOfCountryData = getDataFromApi(null);
            return listOfCountryData;
        } else if (totalData != listOfCountryData.getBody().size()) {
            return listOfCountryData = getDataFromApi(null);
        } else {
            return listOfCountryData;
        }
    }

    public ResponseEntity<ArrayList<?>> getLimitData(Integer limit) throws JsonProcessingException {
        return getDataFromApi(limit);
    }

    public ArrayList<HashMap> getDataByOriginNumber(int originNumber) throws JsonProcessingException {
        return UtilityStatic.getListByOriginNumber((ArrayList<HashMap>) getAllDataCache().getBody(), originNumber);
    }


    public ArrayList<DataBeanEng> getDataByLang(String lang) throws Exception {
        if (lang.equals("eng")) {
            return UtilityStatic.convertListOfDataBeanFromHebToEng((ArrayList<HashMap>) getAllDataCache().getBody());
        } else {
            throw new Exception("The default language is hebrew can use only english lang=eng");
        }
    }

    public ArrayList<String> getDataByFilter(String filter) throws Exception {
        switch (filter) {
            case "id":
            case "symbolOfTheMannaChamber":
            case "regionalCouncilNumber":
            case "table":
            case "nameCity":
            case "nameLocationEng":
            case "regionalName":
            case "iconSnap":
            case "office":
            case "symbolCity":
                return UtilityStatic.getListByFilter((ArrayList<HashMap>) getAllDataCache().getBody(), filter);
            default:
                throw new Exception("Don't know this filter : " + filter + " filter must be one of this :     private Integer Id;" +
                        "    private String Table;" +
                        "    private Integer SymbolCity;" +
                        "    private String NameCity;" +
                        "    private String NameLocationEng;" +
                        "    private Integer IconSnap;" +
                        "    private String IconName;" +
                        "    private Integer SymbolOfTheMannaChamber;" +
                        "    private String Office;" +
                        "    private Integer RegionalCouncilNumber;" +
                        "    private String RegionalName;");
        }
    }

    public Integer getDataTotalFromApi() throws JsonProcessingException {
        ResponseEntity<String> responseData;
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        responseData = restTemplate.getForEntity(urlDataGov + "?resource_id=d4901968-dad3-4845-a9b0-a57d027f11ab", String.class);
        HashMap<String, Object> result = (HashMap<String, Object>) new ObjectMapper().readValue(responseData.getBody(), HashMap.class).get("result");
        return (Integer) result.get("total");
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
