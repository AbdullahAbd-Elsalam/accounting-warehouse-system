package com.masaalbeeahforcontracting.accountingwherehousesystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InvoiceDTO {

    private String invoiceNubmer;

    private LocalDate invoiceDate;

    private Long supplierId;

    private Long projectId;// لو الفاتورة للمشروع
        private String notes;

    private List<InvoiceItemDTO> items;
}
