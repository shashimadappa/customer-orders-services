package com.example.demoCostumer.Service;


import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.demoCostumer.DTO.refundDetails;
import com.example.demoCostumer.Repo.Repository;
import com.example.demoCostumer.model.customer;
@EnableScheduling
@Service
public class service {
    @Autowired
    Repository repository;

    public ResponseEntity updateOrder(Integer custID, customer costumer) {

        double  dis,Amount,totalAmount,s,discountAmount;
        Integer count = repository.getCountByCostumerId(custID);

        customer cust = new customer();
        cust.setCustomerId(custID);
        cust.setCustomerName(costumer.getCustomerName());
        cust.setTotalAmount(costumer.getTotalAmount());
        cust.setNumberOfOrders(count);
        cust.setOrderTime(LocalDateTime.now());

        totalAmount =  costumer.getTotalAmount();

                if ( count > 19) {                 //used 19 because DB starts with 0
                    cust.setCustomerCategory("platinum");

                    dis=20;                       // 20% discount for order more than 20 times
                    s=100-dis;
                    Amount = (s*totalAmount)/100;
                    discountAmount = totalAmount - Amount;

                    System.out.println("20% discount given");
                    cust.setRefundAmount(discountAmount);
                

                }else if(count>9){
                    cust.setCustomerCategory("gold");

                    dis=10;                      // 10%	discount for order more than 10 times
                    s=100-dis;
                    Amount = (s*totalAmount)/100;
                    discountAmount = totalAmount - Amount;
                    System.out.println("10% discount given");
                    cust.setRefundAmount(discountAmount);

                }else{
                    cust.setCustomerCategory("regular");
                    System.out.println(" discount given");
                    cust.setRefundAmount(00.00);       //no off for regular orders below 10 times
                }
                sendMail(count);
                System.out.println("mail sent");
              
        repository.save(cust);
        return ResponseEntity.ok().body(cust);
    }

    private void sendMail(Integer count) {  //sending mail to custumers
        if (count == 8) {
            System.out.println("You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10 discounts!p");
        }else if(count == 18){
            System.out.println("You have placed 19 orders with us. Buy one more stuff and you will be promoted to platinum customer and enjoy 20% discounts!");
        }
        }
    



    //API for getting all the details of order by costumerID
    public ResponseEntity getByCustomerId(Integer custID) {
        List<customer> list = repository.findAllById(custID);
        System.out.println(list);
        return ResponseEntity.ok().body(list);
    }



    //API to get refund amount by orderId
    public ResponseEntity getCustomerRefundAmountById(Integer orderId, refundDetails refundDetails) {

        Optional<customer> details = repository.findRefundAmountByOrderId(orderId);
        if (details.isPresent()) {
            customer detail = details.get();
            refundDetails.setOrderDate(detail.getOrderTime());
            refundDetails.setCustomerId( detail.getCustomerId());
            refundDetails.setOrderId( detail.getOrderId());
            refundDetails.setRefundAmount(detail.getRefundAmount());
            System.out.println(refundDetails);
            return ResponseEntity.ok().body(refundDetails);
        }
         return ResponseEntity.ok().body("no data");
    }

        //API to get refund amount by orderId
        @Scheduled(cron = "0 0 0 * * *")   //scheduled every modnight
        public ResponseEntity getCustomerRefundAmountByIdForDay() {

            List<refundDetails> allDetails = new ArrayList<>();
            List<customer> detail = repository.getRefundAmountByOrderIdforDay();
        
            detail.stream().forEach(Costumer ->{
                // customer cust = new customer();
                System.out.println("getting details for day");
                refundDetails cust = new refundDetails();
                cust.setOrderId(Costumer.getOrderId());
                cust.setCustomerId(Costumer.getCustomerId());
                cust.setRefundAmount(Costumer.getRefundAmount());
                cust.setOrderDate(Costumer.getOrderTime());
                System.out.println(cust);
                allDetails.add(cust);   
            });
                return ResponseEntity.ok().body(allDetails);
        }
}

