package com.masaalbeeahforcontracting.accountingwherehousesystem.dao;

import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
