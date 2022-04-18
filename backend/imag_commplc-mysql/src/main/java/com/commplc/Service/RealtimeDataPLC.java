package com.commplc.Service;

import com.commplc.Constant.SystemPLC;
import com.commplc.Entity.DataEntity;
import com.commplc.Repository.DataRepository;
import com.commplc.Utils.ConnectPLC;
import com.commplc.Utils.ReadDataPLC;
import com.commplc.Utils.WriteDataToExcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Service
public class RealtimeDataPLC {

    public static String shift;

    public static HashMap<String, String>[]  historyData;

    private HashMap<String, String>[] result;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 1000)
    public void readData100Ms() {
        System.out.println("Shceduled");
        updateShift();
        if (ConnectPLC.checkConnect()){
            List<Long> test32 = ReadDataPLC.readDataUnitIntFromPlc(ConnectPLC.getMelsecNet());
            List<Short> test16 = ReadDataPLC.readDataInt16FromPlc(ConnectPLC.getMelsecNet());
            result = new HashMap[SystemPLC.NUMBER_LINE];
            for (int i = 0; i < SystemPLC.NUMBER_LINE; i++) {
                result[i] = caculate(i, test16, test32);
            }
            WriteDataToExcel.writeDataExcel(result);
            historyData = result;
            write(result);
        }
    }

    @Scheduled(fixedDelay = 1000) 
    @SendTo("/topic/publicChatRoom")
    public void sendDataSocket() {
        if (result != null) {
            template.convertAndSend("/topic/publicChatRoom", result);
        } else {
            template.convertAndSend("/topic/publicChatRoom", "{error: 'Connect to PLC faile'}");
        }
    }

    private HashMap<String, String> caculate(Integer line, List<Short> data16, List<Long> data32) {
        HashMap<String, String> result = new HashMap<>();
        Integer numLine = line + 1;
        line = line*SystemPLC.NUMBER_START_DATA_INT16;
        String time = "" + LocalTime.now() + "";
        String date = "" + LocalDate.now() + "";
        Short status = data16.get(line + 1);
        Short speed = data16.get(line + 2);
        Integer baseData32 = (numLine - 1)*SystemPLC.NUMBER_START_DATA_UINT32;
        String counterOut = data32.get(baseData32).toString();
        String runtime = data32.get(baseData32 + 1).toString();

        result.put("time", time);
        result.put("date", date);
        result.put("line", numLine.toString());
        result.put("status", status.toString());
        result.put("counterOut", counterOut);
        result.put("speed", speed.toString());
        result.put("runtime", runtime);

        return  result;
    }

    private void write(HashMap<String, String>[] result) {
        for (HashMap<String, String> aResult : result) {
            DataEntity data = new DataEntity(aResult.get("line"), aResult.get("status"), aResult.get("speed"),
                    aResult.get("counterOut"), aResult.get("runtime"), aResult.get("time"), aResult.get("date"), shift);
            dataRepository.save(data);
        }
    }

    private void updateShift() {
        if (LocalTime.now().isAfter(LocalTime.of(6, 10)) && LocalTime.now().isBefore(LocalTime.of(14, 10))) {
            System.out.println("shift 11111111");
            shift = "shift 1";
        } else if (LocalTime.now().isAfter(LocalTime.of(14, 11)) && LocalTime.now().isBefore(LocalTime.of(22, 10))) {
            shift = "shift 2";
            System.out.println("shift 222222");
        } else if (LocalTime.now().isAfter(LocalTime.of(22, 11)) || LocalTime.now().isBefore(LocalTime.of(6, 10))) {
            shift = "shift 3";
            System.out.println("shift 3333333");
        }
    }

}
