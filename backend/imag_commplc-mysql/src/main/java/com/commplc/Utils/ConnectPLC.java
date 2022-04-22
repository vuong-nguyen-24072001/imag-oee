package com.commplc.Utils;

import HslCommunication.Core.Types.OperateResult;
import HslCommunication.Profinet.Melsec.MelsecMcNet;

import com.commplc.Constant.AdressPlc;

public class ConnectPLC {

    private static MelsecMcNet melsecNet = null;

    public static MelsecMcNet getMelsecNet() {
        return melsecNet;
    }

    public static void setMelsecNet(MelsecMcNet value) {
        melsecNet = value;
    }

    public static void conenct() {
        MelsecMcNet melsec_net = new MelsecMcNet(AdressPlc.ADDRESS, AdressPlc.port);
        melsec_net.setNetworkNumber((byte) 0x00);
        melsec_net.setConnectTimeOut(2000);
        OperateResult connectResult = melsec_net.ConnectServer();
        if (connectResult.IsSuccess) {
            melsecNet = melsec_net;
            System.out.println("Connect to PLC successfully");
        } else {
            melsecNet = null;
            System.out.println("Connect to PLC failed: " + connectResult.Message);
        }
    }

    public static boolean checkConnect() {
        if (melsecNet != null) {
            return true;
        } else {
            System.out.println("Reconnecting to plc .....");
            conenct();
            return false;
        }
    }

}
