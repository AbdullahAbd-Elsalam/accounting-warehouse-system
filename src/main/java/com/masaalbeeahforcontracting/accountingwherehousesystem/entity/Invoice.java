package com.masaalbeeahforcontracting.accountingwherehousesystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Validated
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String invoiceNumber;


    @NotNull(message = "invoice date is required")
    @Column(name = "invoice_date")
    private LocalDate invoiceDate;


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

        // to calculate all items prices
    @Column
    private Double totalBeforeVat;
    @Column
    private Double vatAmount;
    @Column
    private Double totalAfterVat;


    // لتحديد نوع الفاتورة هل هيا مستودع ولا هتدخل علي المشروع


    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type")
    private InvoiceType type; // project or warhouse

    @Column(name = "notes")
    private String notes;

    // العلالقة ما بيبن الفاتورة والاصناف الي جوه الفاتورة

    // هنا بيوضحلي ان استخدام mappedBy بيوضحلي ان العمود ده مش مالك للعلاقة العمود اللي هو invoice item هو اللي مالك للعلاقة بجد
    @JsonManagedReference
    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Invoice_Item> items=new ArrayList<>();

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
