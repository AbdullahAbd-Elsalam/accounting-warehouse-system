package com.masaalbeeahforcontracting.accountingwherehousesystem.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Setter
@Getter

public class InvoiceItemResponseDTO {



    private String itemName;

    private Double quantity ;

  private double totalAmount;

}
