package com.commplc.Utils;

import HslCommunication.Core.Types.OperateResult;
import HslCommunication.Profinet.Melsec.MelsecMcNet;
import com.commplc.Constant.AdressPlc;

public class ConnectPLC {

    private static MelsecMcNet melsecNet = null;

    public static MelsecMcNet getMelsecNet() {
        return melsecNet;
    }

    public static void conenct() {
        MelsecMcNet melsec_net = new MelsecMcNet(AdressPlc.ADDRESS, AdressPlc.port);
        melsec_net.setNetworkNumber((byte) 0x00);
        melsec_net.setConnectTimeOut(2000);
        System.out.println("connect plccc");
        OperateResult connectResult = melsec_net.ConnectServer();
        if (connectResult.IsSuccess) {
            System.out.print("Connect to PLC successfully");
            melsecNet = melsec_net;
        } else {
            System.out.print("Connect to PLC failed: " + connectResult.Message);
            melsecNet = null;
        }
    }

    public static boolean checkConnect() {
        if (melsecNet != null) {
            return true;
        } else {
            conenct();
            return false;
        }
    }

}
