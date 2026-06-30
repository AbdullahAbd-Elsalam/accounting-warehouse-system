package com.masaalbeeahforcontracting.accountingwherehousesystem.controller;

import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundVerification;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dto.InvoiceDTO;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dto.InvoiceItemResponseDTO;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dto.InvoiceResponseDTO;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Invoice;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Supplier;
import com.masaalbeeahforcontracting.accountingwherehousesystem.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

 import java.util.*;

@RestController
@RequestMapping("/api/invoices")
public class invoiceController {


    @Autowired
    private InvoiceService invoiceService;
/*
    @PostMapping("/create")
    private ResponseEntity saveInvoice(@RequestBody InvoiceDTO invoiceDTO)
    {
  //      InvoiceResponseDTO invoiceResponseDTO=new InvoiceResponseDTO();
       Invoice save= invoiceService.saveInvoice(invoiceDTO);
       return ResponseEntity.ok(save);
    }

 */

// create invoice

@PostMapping("/create")
public ResponseEntity<InvoiceResponseDTO> saveInvoice(@RequestBody InvoiceDTO invoiceDTO) {

    Invoice invoice = invoiceService.saveInvoice(invoiceDTO);

    // تحويل الـ Entity لـ ResponseDTO
    InvoiceResponseDTO responseDTO = new InvoiceResponseDTO();
    responseDTO.setId(invoice.getId());
    responseDTO.setInvoiceNumber(invoice.getInvoiceNumber());
    responseDTO.setTotalBeforeVat(invoice.getTotalBeforeVat());
    responseDTO.setVatAmount(invoice.getVatAmount());
    responseDTO.setTotalAfterVat(invoice.getTotalAfterVat());

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
}


// get the invoices by using id
    @GetMapping("/get/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoice(@PathVariable long id){

     Invoice invoice= invoiceService.getInvoiceById(id);

     // return response entity
        InvoiceResponseDTO responseDTO = new InvoiceResponseDTO();
        responseDTO.setId(invoice.getId());
        responseDTO.setInvoiceNumber(invoice.getInvoiceNumber());
        responseDTO.setTotalBeforeVat(invoice.getTotalBeforeVat());
        responseDTO.setVatAmount(invoice.getVatAmount());
        responseDTO.setTotalAfterVat(invoice.getTotalAfterVat());

        List<InvoiceItemResponseDTO> items = invoice.getItems()
                .stream()
                .map(theItem -> {

                    InvoiceItemResponseDTO dto = new InvoiceItemResponseDTO();

                    dto.setItemName(theItem.getItemName());
                    dto.setQuantity(theItem.getQuantity());
                    dto.setTotalAmount(theItem.getTotalAmount());

                    return dto;

                }).toList();

        ;

        responseDTO.setItems(items);


        return ResponseEntity.ok(responseDTO);

}



    // make update for invoices
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody  InvoiceDTO invoiceDTO  , @PathVariable long id ) throws NotFoundVerification {

        invoiceService.updateInvoice(id,invoiceDTO);
    }



}