package com.masaalbeeahforcontracting.accountingwherehousesystem.controller;

import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundException;
import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundVerification;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Supplier;
import com.masaalbeeahforcontracting.accountingwherehousesystem.service.SupplierService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "User API", description = "API for managing users")
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private final SupplierService supplierService ;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // get all supplier
    @GetMapping("/getAllSupplier")
    public List<Supplier> getAllSupplier() throws NotFoundException {

        return supplierService.getAllSupplier();
    }

    // get supplier
    @GetMapping("/getSupplier/{id}")
    public Supplier getSupplier(@PathVariable long id) throws NotFoundVerification {

        return supplierService.findSupplierById(id);
    }


        // make update for supplier
        @PutMapping("/update/{id}")
        public void updateSupplier(@Valid @RequestBody Supplier supplier,@PathVariable long id ) throws NotFoundVerification {
    
          supplierService.updateSupplierDemo(supplier,id);
        }


    @PostMapping("/create")
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier) {

        Supplier savedSupplier = supplierService.saveSupplier(supplier);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }


    // make delete supplier
    @DeleteMapping("/delete/{id}")
    public void deleteSupplier(@PathVariable  long id){
        supplierService.deleteSupplier(id);
    }

}
