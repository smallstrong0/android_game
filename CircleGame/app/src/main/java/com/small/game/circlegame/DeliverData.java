package com.small.game.circlegame;

import java.util.ArrayList;

/**
 * 利用单例模式来传值
 * Created by smallstrong on 16/5/24.
 */
public class DeliverData {

    private static DeliverData deliverData;

    private DeliverData() {

    }

    public static DeliverData getSingleton() {
        if (deliverData == null) {                         //Single Checked
            synchronized (DeliverData.class) {
                if (deliverData == null) {                 //Double Checked
                    deliverData = new DeliverData();
                }
            }
        }
        return deliverData;
    }


    ArrayList<String> dataZhen = new ArrayList<>();
    ArrayList<String> dataDa = new ArrayList<>();

    public ArrayList<String> getDataZhen() {
        return dataZhen;
    }

    public void setDataZhen(ArrayList<String> dataZhen) {
        this.dataZhen = dataZhen;
    }

    public ArrayList<String> getDataDa() {
        return dataDa;
    }

    public void setDataDa(ArrayList<String> dataDa) {
        this.dataDa = dataDa;
    }
}
