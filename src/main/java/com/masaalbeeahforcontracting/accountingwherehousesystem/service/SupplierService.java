package com.masaalbeeahforcontracting.accountingwherehousesystem.service;

import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundException;
import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundVerification;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dao.SupplierRepository;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

// get all supplier
    public List<Supplier> getAllSupplier() throws NotFoundException
    {

        if(supplierRepository.findAll().isEmpty())
            throw new NotFoundException("supplier Not found");
        return supplierRepository.findAll();


    }
    // save all data for supplier
    public Supplier saveSupplier(Supplier supplier){
      return   supplierRepository.save(supplier);
    }

    // find suppler by id
    public  Supplier findSupplierById(long id) throws NotFoundVerification{

        if(id<=0|| String.valueOf(id)==" "){
            throw new NotFoundVerification("the id shoud by bigger than 1 and is numeric ");
        }

        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));    }


    // make update supplier
    public void updateSupplierDemo(Supplier newSupplier,long id) throws NotFoundVerification {
        // get the objcet
        Supplier oldSupplier= supplierRepository.findById(id).orElseThrow(()->new RuntimeException("supplier is not found"));
        oldSupplier.setName(newSupplier.getName());
        oldSupplier.setEmail(newSupplier.getEmail());
        oldSupplier.setPhoneNumber(newSupplier.getPhoneNumber());

        supplierRepository.save(oldSupplier);
    }

    // make delete supplier
    public void deleteSupplier(long id){

        supplierRepository.deleteById(id);

    }




}
