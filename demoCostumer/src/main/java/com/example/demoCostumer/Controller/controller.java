package com.example.demoCostumer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoCostumer.DTO.refundDetails;
import com.example.demoCostumer.Repo.Repository;
import com.example.demoCostumer.Service.service;
import com.example.demoCostumer.model.customer;

@RestController
@RequestMapping("/api")
public class controller {

    @Autowired
    service service;
    @Autowired
    Repository repository;


    //placing a order using customer ID as pathVariable
    @PostMapping("/updateOrder/{custId}")
    public ResponseEntity newOrder(@PathVariable(value = "custId") Integer custID, @RequestBody customer costumer ){
        return service.updateOrder(custID, costumer);
    }

       //API for getting all the orders and detailsof them by costumerID
    @GetMapping("/getByCustomerId/{custId}")
    public ResponseEntity getByCustomerId(@PathVariable(value = "custId") Integer custID){
        return service.getByCustomerId(custID);

    }

     //API to get refund amount by orderId
    @GetMapping("/getCustomerRefundAmountById/{orderId}")
    public ResponseEntity getCustomerRefundAmountById(@PathVariable(value = "orderId") Integer orderId, @RequestBody refundDetails refundDetails){
        return service.getCustomerRefundAmountById(orderId, refundDetails);
    }

      //API to get refund amount by orderId for day
   
      @GetMapping("/getCustomerRefundAmountByIdForDay")
      public ResponseEntity getCustomerRefundAmountByIdForDay(){
          return service.getCustomerRefundAmountByIdForDay();
      }
}
