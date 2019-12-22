package com.datafilter.api;


import com.datafilter.controller.DataController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/" + DataApi.VERSION_API)
public class DataApi {

    public static final double VERSION_API = 1.0;

    @Autowired
    DataController dataController;

    @GetMapping
    public String getVersionApi() {
        return "This api version is : " + VERSION_API;
    }

    /*
    This function return all country data
    @METHOD GET
    @Return ResponseEntity<String>
    @URI /api/1.0/data
     */
    @GetMapping(value = "data")
    public ResponseEntity<ArrayList<?>> getAllData() throws JsonProcessingException {
        return dataController.getAllData();
    }

    /*
    This function return country data with limit row
    @METHOD GET
    @Param = limit (int)
    @Return ResponseEntity<String>
    @URI /api/1.0/data?limit=int
     */
    @GetMapping(value = "data", params = "limit")
    public ResponseEntity<ArrayList<?>> getLimitData(@RequestParam(value = "limit", required = true) int limit) throws JsonProcessingException {
        return dataController.getLimitData(limit);
    }

    /*
    This function return country data with the selected origin number
    @METHOD GET
    @Param = originNumber(int)
    @Return ArrayList<LinkedHashMap>
    @URI /api/1.0/data?limit=int
     */
    @GetMapping(value = "data", params = "originNumber")
    public ArrayList<HashMap> getDataByOriginNumber(@RequestParam(value = "originNumber", required = true) int originNumber) throws JsonProcessingException {
        return dataController.getDataByOriginNumber(originNumber);
    }

    /*
    This function return country data with the selected origin number
    @METHOD GET
    @Param = lang (String)
    @Return ArrayList<?>
    @URI /api/1.0/data?limit=int
     */
    @GetMapping(value = "data", params = "lang")
    public ArrayList<?> getDataByOriginNumber(@RequestParam(value = "lang", required = true) String lang) throws Exception {
        return dataController.getDataByLang(lang);
    }
}
