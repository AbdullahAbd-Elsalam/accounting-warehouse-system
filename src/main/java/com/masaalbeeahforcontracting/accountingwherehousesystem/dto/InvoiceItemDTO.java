package com.masaalbeeahforcontracting.accountingwherehousesystem.dto;

import lombok.Data;

@Data
public class InvoiceItemDTO {


    private String itemName;

    private Double quantity ;

    private Double priceBeforeVat;

    private Double VatPercent;


}
