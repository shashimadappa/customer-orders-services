package com.example.demoCostumer.Repo;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import com.example.demoCostumer.model.customer;

public interface Repository extends JpaRepository<customer, Integer> {

     Optional<customer> findRefundAmountByOrderId(Integer orderId);

     @Query (value = "select count(customer_id) from costumer c where c.customer_id = ?1", nativeQuery = true)
     Integer getCountByCostumerId(Integer custID);

     @Query (value = "select * from costumer c where c.customer_id = ?1", nativeQuery = true)
     List<customer> findAllById(Integer custID);

     

     @Query (value = "SELECT * FROM    costumer c  WHERE   c.order_time >= NOW() - INTERVAL 1 DAY", nativeQuery = true)
     List<customer>  getRefundAmountByOrderIdforDay();


    
}