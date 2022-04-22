package com.commplc.Service;

import com.commplc.Constant.SystemPLC;
import com.commplc.Entity.Line1Entity;
import com.commplc.Repository.Line1Repository;
import com.commplc.Utils.CaculateOEE;
import com.commplc.Utils.ConnectPLC;
import com.commplc.Utils.ReadDataPLC;
import com.commplc.Utils.WriteDataToExcel;
import com.commplc.Variable.TimeVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RealtimeDataPLC {

    public static String shift = "";

    public static HashMap<String, String>[]  historyData;

    private HashMap<String, String>[] result;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private TimeVariable timeVariable;

    @Autowired
    private Line1Repository line1Repository;

    @Scheduled(fixedDelay = 800)
    public void readData100Ms() {
        System.out.println("Shceduled");
        updateShift();
        if (ConnectPLC.getMelsecNet() != null){
            try {
                List<Long> test32 = ReadDataPLC.readDataUnitIntFromPlc(ConnectPLC.getMelsecNet());
                List<Short> test16 = ReadDataPLC.readDataInt16FromPlc(ConnectPLC.getMelsecNet());
                List<Short> targets = ReadDataPLC.readTargetAndSpeedStandardFromPlc(ConnectPLC.getMelsecNet());
                result = new HashMap[SystemPLC.NUMBER_LINE];
                for (int i = 0; i < SystemPLC.NUMBER_LINE; i++) {
                    result[i] = caculate(i, test16, test32, targets);
                }
                WriteDataToExcel.writeDataExcel(result);
                historyData = result;
                write(result);
            } catch (Exception e) {
                ConnectPLC.setMelsecNet(null);
            }
        }  else {
            //test for 1 line
            //caculate downtime when disconnect to plc
            result = new HashMap[SystemPLC.NUMBER_LINE];
            for (int line = 1; line <= SystemPLC.NUMBER_LINE; line++) {
                HashMap<String, String> rs = new HashMap<>();
                Double downtimeMinute = 0.0;
                if (line == 1) {
                    Double usedTimeMinute = timeVariable.getUsedTimeLine(line)/60.0;
                    Double runtimeMinute = timeVariable.getRuntimeMinuteLine(line);
                    if (runtimeMinute != 0.0) {
                        downtimeMinute = Math.floor(usedTimeMinute - runtimeMinute);
                    } else {
                        downtimeMinute = Math.floor(usedTimeMinute);
                    }
                    System.out.println("runtime when disconnect plc: " + runtimeMinute);
                    System.out.println("downtimeMinute when disconnect plc: " + downtimeMinute);
                }
                rs.put("status", "Stop");
                rs.put("downtime", downtimeMinute.toString());
                rs.put("plc", "disconnect");
                result[line - 1] = rs;
            }
            ConnectPLC.setMelsecNet(null);
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

    @Scheduled(fixedDelay = 500) 
    public void reconnectPLC() {
        if (ConnectPLC.getMelsecNet() == null) {
            System.out.println("Reconnceting to PLC ...");
            ConnectPLC.conenct();
        }
    }

    @Scheduled(fixedDelay = 900)
    public void caculateUsedTime() {

        for (int line = 1; line < SystemPLC.NUMBER_LINE; line++) {
            // demo 1 line (line 1)
            if (line == 1) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeMark = timeVariable.getMarkTime(1);
                String timeNow = LocalDate.now().toString() + " " + LocalTime.now().toString();
                try {
                    Date mark = format.parse(timeMark);
                    Date now = format.parse(timeNow);
                    Long diff = now.getTime() - mark.getTime();
                    Long diffSeconds = diff / 1000 % 60;  
                    Long diffMinutes = diff / (60 * 1000) % 60; 
                    Long diffHours = diff / (60 * 60 * 1000);
                    timeVariable.setUsedTimeLine(line, diffHours*3600 + diffMinutes*60 + diffSeconds);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private HashMap<String, String> caculate(Integer line, List<Short> data16, List<Long> data32, List<Short> targets) {
        HashMap<String, String> result = new HashMap<>();
        Integer numLine = line + 1;
        Short target = targets.get(line);
        Short speedStandard = targets.get(line + SystemPLC.NUMBER_LINE);
        line = line*SystemPLC.NUMBER_START_DATA_INT16;
        String time = "" + LocalTime.now() + "";
        String date = "" + LocalDate.now() + "";
        Short status = data16.get(line + 1);
        Short speed = data16.get(line + 2);
        Integer baseData32 = (numLine - 1)*SystemPLC.NUMBER_START_DATA_UINT32;
        Long counterOut = data32.get(baseData32);
        Long runtime = data32.get(baseData32 + 1);
        //test 1 line (line 1)
        Long downtime = 0L;
        Double runtimeMinute = 0.0;
        Double downtimeMinute = 0.0;
        Double available = 0.0;
        Double perfomance = 0.0;
        Double quantity = 0.0;
        Double oee = 0.0;
        Double oee1 = 0.0;
        if (numLine == 1) {
            Long usedTime = timeVariable.getUsedTimeLine(numLine);
            if (usedTime != 0 && usedTime > 0) {
                downtime = timeVariable.getUsedTimeLine(numLine) - runtime;
            }
            runtimeMinute = Math.ceil(runtime/60.0);
            downtimeMinute = Math.floor(downtime/60.0);
            System.out.println("used time line 1: " + usedTime + "usedtime minute: " + usedTime/60.0);
            System.out.println("runtime line 1: " + runtime + "runtime minute (lam tron len): " + runtimeMinute);
            System.out.println("downtime line 1:" + downtime + "downtime minute (lam tron xuong): " + downtimeMinute);
            available = CaculateOEE.Available(runtimeMinute, downtimeMinute);
            perfomance = CaculateOEE.Performance(counterOut, speedStandard, runtimeMinute, available);
            quantity = CaculateOEE.Quantity();
            oee = CaculateOEE.OEE(available, perfomance, quantity)*100;
            oee1 = 100 - oee;
            available *= 100;
            perfomance *= 100;
            quantity *= 100;
        }

        result.put("time", time);
        result.put("date", date);
        result.put("line", numLine.toString());
        result.put("status", status.toString());
        result.put("counterOut", counterOut.toString());
        result.put("speed", speed.toString());
        result.put("runtime", runtimeMinute.toString());
        result.put("target", target.toString());
        result.put("downtime", downtimeMinute.toString());
        result.put("speedStandard", speedStandard.toString());
        result.put("available", Long.toString(Math.round(available)));
        result.put("performance", Long.toString(Math.round(perfomance)));
        result.put("quantity", Long.toString(Math.round(quantity)));
        result.put("oee", Long.toString(Math.round(oee)));
        result.put("oee1", Long.toString(Math.round(oee1)));

        timeVariable.setRuntimeMinuteLine(numLine, runtimeMinute);

        return  result;
    }

    private void write(HashMap<String, String>[] result) {
        for (HashMap<String, String> aResult : result) {
            // DataEntity data = new DataEntity(aResult.get("line"), aResult.get("status"), aResult.get("speed"),
            //         aResult.get("counterOut"), aResult.get("runtime"), aResult.get("time"), aResult.get("date"), shift, aResult.get("target"));
            // dataRepository.save(data);
            switch(aResult.get("line")) {
                case "1":
                    line1Repository.save(new Line1Entity(aResult.get("line"), aResult.get("status"), aResult.get("speed"),
                    aResult.get("counterOut"), aResult.get("runtime"), aResult.get("time"), aResult.get("date"), shift, aResult.get("target")));
                    break;
            }
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
