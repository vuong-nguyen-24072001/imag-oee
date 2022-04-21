package com.commplc.Utils;

public class CaculateOEE {

    public static Double Available(Double runtime, Double downtime) {
        return runtime/(runtime + downtime);
    }

    public static Double Performance(Long counterOut, Short speedStandard, Double runtime, Double available) {
        return (counterOut/(speedStandard*(runtime + available)));
    }

    public static Double Quantity() {
        return 1.0;
    }

    public static Double OEE(Double available, Double perfomance, Double quantity) {
        return available*perfomance*quantity;
    }
    
}
