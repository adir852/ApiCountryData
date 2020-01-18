package com.datafilter.jobthread;

import com.datafilter.controller.DataController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ThreadNumberCity implements Runnable {
    @Autowired
    DataController dataController;


    Logger logger = LoggerFactory.getLogger(Logger.class);


    @Override
    public void run() {
        while (true) {
            try {
                if (dataController == null) {
                    dataController = new DataController();
                }
                //For test
//                double totalLimit = (dataController.getDataTotalFromApi() - Math.random() * (1000 - 1));
                int totalLimit = dataController.getDataTotalFromApi();
                if (totalLimit != dataController.totalData) {
                    int temp = dataController.totalData;
                    dataController.totalData = (int) totalLimit;
                    logger.info("Thread Message The total updated from : " + temp + " to : " + dataController.totalData);
                }
                Thread.sleep(60000);

            } catch (Exception e) {
                logger.error("Thread ERROR : Start StackTrace");
                e.printStackTrace();
                logger.error("Thread ERROR : End StackTrace");
            }
        }
    }
}
