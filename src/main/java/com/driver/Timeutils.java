package com.driver;

public class Timeutils {
    private Timeutils(){

    }
    public static int convertIt(String deliveryTime){
        String[] time=deliveryTime.split(":");
        return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }
    public static String getDeliveryTimeAsString(int deliveryTime){
        int hours=deliveryTime/60;
        int min=deliveryTime%60;
        String hh=String.valueOf(hours);
        String mm=String.valueOf(min);
        if(hh.length()==1){
            hh='0'+hh;
        }
        if(mm.length()==1){
            mm='0'+mm;
        }
        return hh+":"+mm;
    }
}
