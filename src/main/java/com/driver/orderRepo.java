package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class orderRepo {
   private HashMap<String,Order> ordermap=new HashMap<>();
  private  HashMap<String,DeliveryPartner> partnermap=new HashMap<>();
   private HashMap<String,String> orderpartner=new HashMap<>();
   private HashMap<String, List<String>> partnerorder=new HashMap<String, List<String>>();
    public void addMyorder(Order order) {
        ordermap.put(order.getId(),order);
    }

    public void addThisPt(DeliveryPartner dd) {
        partnermap.put(dd.getId(),dd);
    }

    public void addPair(String orderId, String partnerId) {
        List<String> orders=partnerorder.getOrDefault(partnerId,new ArrayList<String>());
        orders.add(orderId);
        partnerorder.put(partnerId,orders);
        orderpartner.put(orderId,partnerId);
    }

    public Optional<Order> getOrderbyId(String orderId) {
        if(ordermap.containsKey(orderId)){
            return Optional.of(ordermap.get(orderId));
        }
        return Optional.empty();
    }

    public Optional<DeliveryPartner> getpartnerByid(String partnerId) {
        if(partnermap.containsKey(partnerId)){
            return Optional.of(partnermap.get(partnerId));
        }
        return Optional.empty();
    }

    public List<String> getOrders(String partnerId) {
        return partnerorder.get(partnerId);
    }

    public List<String> getAllOrd() {
        List<String> orders=new ArrayList<>();
        for(String Id:ordermap.keySet()){
            orders.add(Id);
        }
        return orders;
    }

    public Integer getunAssigned() {
        int ans=0;
        for(String id:ordermap.keySet()){
            if(orderpartner.containsKey(id)==false){
                ans++;
            }
        }
        return ans;
    }

    public void deletePartner(String partnerId) {
        partnermap.remove(partnerId);
        partnerorder.remove(partnerId);
    }

    public void unassignOrder(String id) {
        orderpartner.remove(id);
    }

    public void deleteOrder(String orderId) {
        ordermap.remove(orderId);
        orderpartner.remove(orderId);
    }

    public String getPartnerForOrderId(String orderId) {
        return orderpartner.get(orderId);
    }

    public void unassignPartner(String partnerId,String orderId) {
        List<String> orderIds=partnerorder.get(partnerId);
        orderIds.remove(orderId);
        partnerorder.put(partnerId,orderIds);
    }
}
