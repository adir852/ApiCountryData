package com.datafilter.datafilter.api;


import com.datafilter.datafilter.controller.DataController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
    //For lerning see two way to set limit
//          @RequestParam(value = "limit", required = false) int limit
//        if (limit > 0) {
//            return dataController.getLimitData(limit);
//        } else {
//            return dataController.getAllData();
//        }
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

}
