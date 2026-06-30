package com.masaalbeeahforcontracting.accountingwherehousesystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Validated
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;


    @Email
    @Column(unique = true)
    private String email;

    @Size(max = 50)
    @Column(nullable = true)
    private String phoneNumber;


    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    public Supplier(String name , String email, String phoneNumber)
    {

        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
}
