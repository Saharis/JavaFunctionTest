package com.virgil.math;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/3/5.
 */
public class TestMath {
    public static void main(String[] args){
        LogUtil.printlnInConsle(getTravelMoneyFloor(33,333));
    }
    private static String getTravelMoneyFloor(int travelPrice, int walletPrice) {
        float realTravelPrice = travelPrice / 100.0f;
        float realwalletPrice = walletPrice / 100.0f;
        return String.format("%1$.2f", Math.floor(realTravelPrice));
    }

}
