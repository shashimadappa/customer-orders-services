package com.example.demoCostumer.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "costumer")
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderId;

    Integer customerId;

    String customerName;
    
    String customerCategory;

    Double totalAmount;

    Double refundAmount;

    Integer numberOfOrders;

    LocalDateTime orderTime;

    
}
