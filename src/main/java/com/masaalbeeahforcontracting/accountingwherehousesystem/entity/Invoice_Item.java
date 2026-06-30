package com.masaalbeeahforcontracting.accountingwherehousesystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "invoice_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Validated
@Builder
public class Invoice_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // the name of item
    private String itemName;

    // the quantit of item in invoice
    private Double quantity;

    // price before vat
    private Double priceBeforeVat;
// ضريبة القيمة المضافة في المملكة السعودية ده النسبة بتاعته
    private Double VatPercent;

    private Double VatAmount;

    // the total of price
    private Double totalAmount;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}
