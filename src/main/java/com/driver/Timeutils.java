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
        return String.format("%s:%s",String.valueOf(hours),String.valueOf(min));
    }
}
