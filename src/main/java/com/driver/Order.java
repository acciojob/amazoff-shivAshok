package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
           this.id=id;
           this.deliveryTime=convertIt(deliveryTime);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }
    private int convertIt(String deliveryTime){
        String[] time=deliveryTime.split(":");
        return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }
    private String getDeliveryTimeAsString(){
        int hours=deliveryTime/60;
        int min=deliveryTime%60;
        return String.format("%s:%s",String.valueOf(hours),String.valueOf(min));
    }
    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
    public void SetDeliveryTime(String dd){
        this.deliveryTime=convertIt(dd);
    }
    public void setDeliveryTime(int time){
        this.deliveryTime=time;
    }
}
