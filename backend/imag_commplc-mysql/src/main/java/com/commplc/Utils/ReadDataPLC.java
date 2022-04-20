package com.commplc.Utils;

import HslCommunication.Core.Types.OperateResultExOne;
import HslCommunication.Profinet.Melsec.MelsecMcNet;
import com.commplc.Constant.SystemPLC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadDataPLC {

    public static Long counterDoubleCheck;

    public static List<Short> readDataIntFromPlc(MelsecMcNet melsec_net) {
        OperateResultExOne<short[]> read1 = melsec_net.ReadInt16("D101",(short) 240);
        List<Short> result = new ArrayList<>();
        for (int i = 0; i < read1.Content.length; i++) {
            result.add(read1.Content[i]);
        }

        if (!read1.IsSuccess) {
            System.out.print(read1.Message);
        }
        System.out.println(result.size());
        return result;
    }


    public static Long keepDataCounterLine1(MelsecMcNet melsec_net) {
        counterDoubleCheck =  melsec_net.ReadUInt32("D1038").Content;
        return counterDoubleCheck;
    }

    public static boolean doubleCheckReset(MelsecMcNet melsec_net) {

        Long checkCounterOut = melsec_net.ReadUInt32("D1038").Content;
        if (checkCounterOut < counterDoubleCheck) {
            return true;
        }
        return false;

    }

    public static List<Short> readDataInt16FromPlc(MelsecMcNet melsec_net) {
        int quantity = SystemPLC.NUMBER_START_ADDRESS + SystemPLC.NUMBER_START_DATA_INT16*SystemPLC.NUMBER_LINE - 1000;
        OperateResultExOne<short[]> data = melsec_net.ReadInt16(SystemPLC.ADDRESS_START, (short) quantity);
        List<Short> result = new ArrayList<>();
        for (int i = 0; i < data.Content.length; i++) {
            result.add(data.Content[i]);
        }
        return result;
    }

    public static List<Long> readDataUnitIntFromPlc(MelsecMcNet melsec_net) {
        int quantity = SystemPLC.NUMBER_LINE*SystemPLC.NUMBER_START_DATA_UINT32;
        OperateResultExOne<long[]> data = melsec_net.ReadUInt32(SystemPLC.ADDRESS_START_READUINT32, (short) quantity);
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < data.Content.length; i++) {
            result.add(data.Content[i]);
        }
        return result;
    }

    public static List<Short> readTargetFromPlc(MelsecMcNet melsec_net) {
        OperateResultExOne<short[]> data = melsec_net.ReadInt16("D1111", (short) 12);
        List<Short> result = new ArrayList<>();
        for (int i = 0; i < data.Content.length; i++) {
            result.add(data.Content[i]);
        }
        return result;
    }

    public static List<Integer> readDataFake() {
        List<Integer> result = new ArrayList<>();
        Random rd = new Random();
        int[] data = new int[480];
        for (int i = 0; i < SystemPLC.NUMBER_OF_ONE_LINE*12*2; i++) {
            data[i] = rd.nextInt();
        }
        for (int i = 0; i < data.length; i++) {
            if (i%2==0) {
                result.add(data[i]);
            }
        }
        return result;
    }

}
