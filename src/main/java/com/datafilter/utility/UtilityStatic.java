package com.datafilter.utility;

import com.datafilter.bean.DataBeanEng;
import com.datafilter.bean.DataBeanHeb;

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

    public static ArrayList<String> getListByFilter(ArrayList<HashMap> ListdataBeanHeb, String filter) {
        switch (filter) {
            case "id":
                return getListByFilterName(ListdataBeanHeb, "_id");
            case "table":
                return getListByFilterName(ListdataBeanHeb, "טבלה");
            case "symbolCity":
                return getListByFilterName(ListdataBeanHeb, "סמל_ישוב");
            case "nameCity":
                return getListByFilterName(ListdataBeanHeb, "שם_ישוב");
            case "nameLocationEng":
                return getListByFilterName(ListdataBeanHeb, "שם_ישוב_לועזי");
            case "iconSnap":
                return getListByFilterName(ListdataBeanHeb, "סמל_נפה");
            case "iconName":
                return getListByFilterName(ListdataBeanHeb, "שם_נפה");
            case "symbolOfTheMannaChamber":
                return getListByFilterName(ListdataBeanHeb, "סמל_לשכת_מנא");
            case "office":
                return getListByFilterName(ListdataBeanHeb, "לשכה");
            case "regionalCouncilNumber":
                return getListByFilterName(ListdataBeanHeb, "סמל_מועצה_איזורית");
            case "regionalName":
                return getListByFilterName(ListdataBeanHeb, "שם_מועצה");
            default:
                return null;
        }
    }

    private static ArrayList<String> getListByFilterName(ArrayList<HashMap> ListdataBeanHeb, String name) {
        ArrayList<String> arrayListFilter = new ArrayList<>();
        for (int i = 0; i < ListdataBeanHeb.size(); i++) {
            arrayListFilter.add((ListdataBeanHeb.get(i).get(name)).toString());
        }
        return arrayListFilter;
    }
}
