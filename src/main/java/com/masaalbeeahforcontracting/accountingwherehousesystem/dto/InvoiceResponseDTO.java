package com.masaalbeeahforcontracting.accountingwherehousesystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceResponseDTO {

    private long id;
    private String invoiceNumber;
    private Double totalBeforeVat;
    private Double vatAmount;
    private Double totalAfterVat;


    private List<InvoiceItemResponseDTO> items;
}
