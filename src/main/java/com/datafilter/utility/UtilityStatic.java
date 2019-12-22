package com.datafilter.utility;

import com.datafilter.bean.DataBeanEng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class UtilityStatic {

    public static ArrayList<HashMap> getListByOriginNumber(ArrayList<HashMap> ListdataBeanHeb, int originNumber) {
        ArrayList<HashMap> listFixByOriginNumber = new ArrayList<>();
        for (int i = 0; i < ListdataBeanHeb.size(); i++) {
            int symboleOriginNumber = (int) ListdataBeanHeb.get(i).get("סמל_מועצה_איזורית");
            if (symboleOriginNumber == originNumber) {
                listFixByOriginNumber.add(ListdataBeanHeb.get(i));
            }
        }
        return listFixByOriginNumber;
    }

    public static ArrayList<DataBeanEng> convertListOfDataBeanFromHebToEng(ArrayList<HashMap> ListdataBeanHeb) {
        ArrayList<DataBeanEng> ListDataBeanEng = new ArrayList<>();
        for (int i = 0; i < ListdataBeanHeb.size(); i++) {
            DataBeanEng dataBeanEng = new DataBeanEng();
            dataBeanEng.setId((int) ListdataBeanHeb.get(i).get("_id"));
            dataBeanEng.setTable((String) ListdataBeanHeb.get(i).get("טבלה"));
            dataBeanEng.setSymbolCity((int) ListdataBeanHeb.get(i).get("סמל_ישוב"));
            dataBeanEng.setNameCity((String) (ListdataBeanHeb.get(i).get("שם_ישוב")));
            dataBeanEng.setNameLocationEng((String) ListdataBeanHeb.get(i).get("שם_ישוב_לועזי"));
            dataBeanEng.setIconSnap((int) ListdataBeanHeb.get(i).get("סמל_נפה"));
            dataBeanEng.setIconName((String) ListdataBeanHeb.get(i).get("שם_נפה"));
            dataBeanEng.setSymbolOfTheMannaChamber((int) ListdataBeanHeb.get(i).get("סמל_לשכת_מנא"));
            dataBeanEng.setOffice((String) ListdataBeanHeb.get(i).get("לשכה"));
            dataBeanEng.setRegionalCouncilNumber((int) ListdataBeanHeb.get(i).get("סמל_מועצה_איזורית"));
            dataBeanEng.setRegionalName((String) ListdataBeanHeb.get(i).get("שם_מועצה"));

            ListDataBeanEng.add(dataBeanEng);
        }
        return ListDataBeanEng;

    }
}
