package com.driver;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class orderService {
    @Autowired
    private orderRepo repo=new orderRepo();
    public void addOrDer(Order order) {
       repo.addMyorder(order);
    }

    public void addpartner(String partnerId) {
        DeliveryPartner dd=new DeliveryPartner(partnerId);
        repo.addThisPt(dd);
    }

    public void addorderPartnerPair(String orderId, String partnerId) {
        Optional<Order> orderOpt=repo.getOrderbyId(orderId);
        Optional<DeliveryPartner> partnerOpt=repo.getpartnerByid(partnerId);
        if(orderOpt.isEmpty()){
            throw new RuntimeException("orderid invalid");
        }
        if(partnerOpt.isEmpty()){
            throw new RuntimeException("partnerid invalid");
        }
        DeliveryPartner partner=partnerOpt.get();
        partner.setNumberOfOrders(partner.getNumberOfOrders()+1);
        repo.addThisPt(partner);
        repo.addPair(orderId,partnerId);
    }

    public Optional<Order> getOrderBYid(String orderId) throws RuntimeException {
        Optional<Order> optional=repo.getOrderbyId(orderId);
        if(optional.isEmpty()){
            throw new RuntimeException("invalid Id");
        }
        return optional;
    }

    public int getCountPartnerbyid(String partnerId) {
        List<String> orders = repo.getOrders(partnerId);
        return orders.size();
    }
    public List<String> getOrdersbyParter(String partnerId) {
        Optional<DeliveryPartner> tt=repo.getpartnerByid(partnerId);
        if(tt.isPresent()){
            return repo.getOrders(partnerId);
        }
        return new ArrayList<>();
    }

    public List<String> getAllOrder() {
       return  repo.getAllOrd();
    }

    public Integer getUnassigned() {
        return repo.getunAssigned();
    }

    public Integer getNumberOrderAfterTime(String time, String partnerId) {
        List<String> orderId=repo.getOrders(partnerId);
        int count=0;
        for(String id:orderId){
            Order order=repo.getOrderbyId(id).get();
            if(order.getDeliveryTime()>Timeutils.convertIt(time)){
                count++;
            }
        }
        return count;
    }

    public String LastDeliveryTime(String partnerId) {
        List<String> orders=repo.getOrders(partnerId);
        int max=0;
        for(String id:orders){
            int deliveryTime=repo.getOrderbyId(id).get().getDeliveryTime();
            if(deliveryTime>max){
                max=deliveryTime;
            }
        }
        return Timeutils.getDeliveryTimeAsString(max);
    }

    public void deleteThisPartner(String partnerId) {

        if(Objects.nonNull(partnerId)) {
            List<String> orderIds = repo.getOrders(partnerId);
            for (String id : orderIds) {
                repo.unassignOrder(id);
            }
        }
        repo.deletePartner(partnerId);
    }

    public void deleteOrderId(String orderId) {
        String partnerId=repo.getPartnerForOrderId(orderId);
        repo.deleteOrder(orderId);
        repo.unassignPartner(partnerId,orderId);
    }

    public Optional<DeliveryPartner> getPartnerbyid(String partnerId) {
        Optional<DeliveryPartner> deliveryPartner=repo.getpartnerByid(partnerId);
        if(deliveryPartner.isEmpty()){
            throw new RuntimeException("invalid id");
        }
        return deliveryPartner;
    }
}
