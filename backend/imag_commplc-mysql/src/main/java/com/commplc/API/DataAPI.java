package com.commplc.API;

import com.commplc.Constant.UrlSystem;
import com.commplc.Entity.HistoryLine1Entity;
import com.commplc.Entity.Line1Entity;
import com.commplc.Service.IHistoryLine1Service;
import com.commplc.Service.ILine1Service;
import com.commplc.Service.RealtimeDataPLC;
import com.commplc.Utils.*;
import com.commplc.Variable.TimeVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import HslCommunication.Profinet.Melsec.MelsecMcNet;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class DataAPI {

    @Autowired
    private ILine1Service line1Service;

    @Autowired
    private IHistoryLine1Service historyLine1Service;

    @Autowired
    private TimeVariable timeVariable;

    @Deprecated
    @GetMapping("/getData")
    public HashMap<String, HashMap<String, Integer>> getDataRead() {
        HashMap<String, Integer> dataPlc = new HashMap<>();
        dataPlc.put("D100", 100);
        dataPlc.put("D101", 101);
        dataPlc.put("D102", 102);
        dataPlc.put("D103", 103);
        HashMap<String, HashMap<String, Integer>> response = new HashMap<>();
        Line1Entity data = new Line1Entity("1","1","6000", "40000", "20000", "20", "sdf", RealtimeDataPLC.shift, "123");
        line1Service.save(data);
        response.put("dataplc", dataPlc);
        return response;
    }

    @Deprecated
    @GetMapping("/resetData")
    @Transactional
    public HashMap<String, String> resetData() throws InterruptedException {
        //String pathDataHistory = "./src/main/resources/DataExcel/DataHistory/OEE_" + LocalDate.now() + "_" + RealtimeDataPLC.shift.replace(" ", "-") + ".sql";
        HashMap<String, String> response = new HashMap<>();
        MelsecMcNet melsec_net = ConnectPLC.getMelsecNet();
        ReadDataPLC.keepDataCounterLine1(melsec_net);
        int timeout = 0;
        if (ConnectPLC.checkConnect()){
            while (true) {
                try {
                    melsec_net.Write( "D1101", 1);
                    timeout += 1;
                    if (timeout == 6) {
                        response.put("status", "faile");
                        response.put("error", "Timeout");
                        return response;
                    }
                    if (ReadDataPLC.doubleCheckReset(melsec_net)) {
                        String line = "line1";
                        int numLine = 1;
                        UrlSystem.setPathDataHistory(line);
                        timeVariable.setMarkTime(numLine, "");
                        Database.backup("root","vuongnguyen04040707","oee-db","line1", UrlSystem.pathDataHistory);
                        // for deploy to mysql docker
                        // Database.backup("root","root","oee-db",UrlSystem.pathDataHistory);
                        line1Service.truncateTable();
                        switch(numLine) {
                            case 1: 
                                saveHistory(RealtimeDataPLC.historyData[numLine - 1], response);
                                break;
                        }
                        melsec_net.Write( "D1101", 0);
                        response.put("status", "success");
                        response.put("content", "reset success");
                        return response;
                    }
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Connect PLC failed, trying to connect to PLC again ...");
            return response;
        }
    }

    @PostMapping("/setSpecification")
    public HashMap<String, String> setSpecification(@RequestBody HashMap<String, String> body) {
        System.out.println("asdfsdfsdfssfsdsfsdf" + body);
        if (ConnectPLC.checkConnect()){
            WriteDataToPLC.writeDataToPlc(body.get("targetAddress").toString(), Integer.parseInt(body.get("target").toString()), ConnectPLC.getMelsecNet());
            WriteDataToPLC.writeDataToPlc(body.get("speedAddress").toString(), Integer.parseInt(body.get("speed").toString()), ConnectPLC.getMelsecNet());
        } else {
            System.out.println("Connect PLC failed, trying to connect to PLC again ...");
        }
        HashMap<String, String> response = new HashMap<>();
        return response;
    }

    public void saveHistory(HashMap<String, String> aResult, HashMap<String, String> response) {
        HistoryLine1Entity data = new HistoryLine1Entity(aResult.get("line"), aResult.get("status"), aResult.get("speed"),
                aResult.get("counterOut"), aResult.get("runtime"), aResult.get("time"), aResult.get("date"), RealtimeDataPLC.shift);
                historyLine1Service.save(data);
        response.put("status", "success");
    }

}
