package com.commplc.API;

import com.commplc.Entity.DataEntity;
import com.commplc.Entity.HistoryEntity;
import com.commplc.Service.IDataService;
import com.commplc.Service.IHistoryService;
import com.commplc.Service.RealtimeDataPLC;
import com.commplc.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import HslCommunication.Profinet.Melsec.MelsecMcNet;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
public class DataAPI {

    @Autowired
    private IDataService dataService;

    @Autowired
    private IHistoryService historyService;

    @Deprecated
    @GetMapping("/getData")
    public HashMap<String, HashMap<String, Integer>> getDataRead() {
        HashMap<String, Integer> dataPlc = new HashMap<>();
        dataPlc.put("D100", 100);
        dataPlc.put("D101", 101);
        dataPlc.put("D102", 102);
        dataPlc.put("D103", 103);
        HashMap<String, HashMap<String, Integer>> response = new HashMap<>();
        DataEntity data = new DataEntity("1","1","6000", "40000", "20000", "20", "sdf", RealtimeDataPLC.shift);
        dataService.save(data);
        response.put("dataplc", dataPlc);
        return response;
    }

    @Deprecated
    @GetMapping("/resetData")
    @Transactional
    public HashMap<String, String> resetData() throws InterruptedException {
        String pathDataHistory = "./src/main/resources/DataExcel/DataHistory/OEE_" + LocalDate.now() + "_" + RealtimeDataPLC.shift.replace(" ", "-") + ".sql";
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
                        Database.backup("root","vuongnguyen04040707","oee-db",pathDataHistory);
                        // for deploy to mysql docker
                        //Database.backup("root","root","oee-db",pathDataHistory);
                        dataService.truncateTable();
                        saveHistory(RealtimeDataPLC.historyData, response);
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

    @GetMapping("/writeData")
    public void writeData() {
        if (ConnectPLC.checkConnect()){
            WriteDataToPLC.writeDataToPlc("D1100", 1, ConnectPLC.getMelsecNet());
        } else {
            System.out.println("Connect PLC failed, trying to connect to PLC again ...");
        }
    }

    @PostMapping("/setSpecification")
    public HashMap<String, String> setSpecification(@RequestBody HashMap<String, String> body) {
        System.out.println("asdfsdfsdfssfsdsfsdf" + body);
        HashMap<String, String> response = new HashMap<>();
        return response;
    }

    public void saveHistory(HashMap<String, String>[] result, HashMap<String, String> response) {
        for (HashMap<String, String> aResult : result) {
            HistoryEntity data = new HistoryEntity(aResult.get("line"), aResult.get("status"), aResult.get("speed"),
                    aResult.get("counterOut"), aResult.get("runtime"), aResult.get("time"), aResult.get("date"), RealtimeDataPLC.shift);
                    historyService.save(data);
        }
        response.put("status", "success");
    }

}
