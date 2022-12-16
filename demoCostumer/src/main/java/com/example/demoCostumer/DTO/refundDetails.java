package com.example.demoCostumer.DTO;


import java.time.LocalDateTime;


import lombok.Data;

@Data
public class refundDetails {

    Integer orderId;

    Integer customerId;

    Double refundAmount;

    LocalDateTime orderDate;
    
}
