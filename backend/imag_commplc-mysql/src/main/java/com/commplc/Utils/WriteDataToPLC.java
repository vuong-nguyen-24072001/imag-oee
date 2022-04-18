package com.commplc.Utils;

import HslCommunication.Profinet.Melsec.MelsecMcNet;

public class WriteDataToPLC {

    public static void writeDataToPlc(String address, boolean[] content) {
        MelsecMcNet melsec_net = ConnectPLC.getMelsecNet();
        melsec_net.Write(address, content);
    }

    public static void writeDataToPlc(String address, int content, MelsecMcNet melsec_net) {
        melsec_net.Write(address, content);
    }

    public static void writeDataToPlc(String address, long content) {
        MelsecMcNet melsec_net = ConnectPLC.getMelsecNet();
        melsec_net.Write(address, content);
    }

    public static void writeDataToPlc(String address, String content) {
        MelsecMcNet melsec_net = ConnectPLC.getMelsecNet();
        melsec_net.Write(address, content);
    }

}
