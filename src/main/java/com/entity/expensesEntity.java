package com.entity;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class expensesEntity {
  
	String title;   
	String vendor;        
	String category;     
	String amount;        
	String paymentMode;   
	LocalDate expenseDate; 
}
