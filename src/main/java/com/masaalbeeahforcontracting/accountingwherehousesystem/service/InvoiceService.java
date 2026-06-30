package com.masaalbeeahforcontracting.accountingwherehousesystem.service;

import com.masaalbeeahforcontracting.accountingwherehousesystem.dao.InvoiceRepository;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dao.SupplierRepository;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dto.InvoiceDTO;
import com.masaalbeeahforcontracting.accountingwherehousesystem.dto.InvoiceItemDTO;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Invoice;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.InvoiceType;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Invoice_Item;
import com.masaalbeeahforcontracting.accountingwherehousesystem.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    // get the static vat
    private static final double DEFUALT_VAT_PERCENT=0.15;

     // get invoice repo
    @Autowired
    private InvoiceRepository invoiceRepository;

    // get supplier repo
    @Autowired
    private SupplierRepository supplierRepository;

    public Invoice saveInvoice(InvoiceDTO dto){

        // جلب المورد
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier Not Found"));

        // إنشاء الفاتورة
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(dto.getInvoiceNubmer()); // صححت الاسم
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setCreateAt(LocalDateTime.now());
        invoice.setSupplier(supplier);
        invoice.setNotes(dto.getNotes());
        invoice.setType(InvoiceType.PROJECT);

        double totalBefore = 0;
        double totalVat = 0;



        List<Invoice_Item> items = new ArrayList<>();

        // تحويل كل InvoiceItemDTO لـ Invoice_Item وحساب الضريبة والمجموع
        for (InvoiceItemDTO itemDto : dto.getItems()) {

            Invoice_Item invoiceItem = new Invoice_Item();
            invoiceItem.setItemName(itemDto.getItemName());
            invoiceItem.setQuantity(itemDto.getQuantity());
            invoiceItem.setPriceBeforeVat(itemDto.getPriceBeforeVat());

            // لو VATPercent null، نخليه 0
            double vatPercent = itemDto.getVatPercent() != null ? itemDto.getVatPercent() : DEFUALT_VAT_PERCENT;
            invoiceItem.setVatPercent(vatPercent);

            // حساب الضريبة والمجموع
            double itemVat = itemDto.getQuantity() * itemDto.getPriceBeforeVat() * vatPercent / 100;
            double itemTotal = (itemDto.getQuantity() * itemDto.getPriceBeforeVat()) + itemVat;

            invoiceItem.setVatAmount(itemVat);
            invoiceItem.setTotalAmount(itemTotal);

            // ربط العنصر بالفاتورة
            invoiceItem.setInvoice(invoice);

            items.add(invoiceItem);

            // تحديث المجموع الكلي للفاتورة
            totalBefore += itemDto.getPriceBeforeVat() * itemDto.getQuantity();
            totalVat += itemVat;
        }

        // إضافة العناصر للفواتير
        invoice.setItems(items);

        // حساب المجموع النهائي للفاتورة
        invoice.setTotalBeforeVat(totalBefore);
        invoice.setVatAmount(totalVat);
        invoice.setTotalAfterVat(totalBefore + totalVat);

        // حفظ الفاتورة مع كل العناصر في الداتابيز
        return invoiceRepository.save(invoice);
    }


    // get invoice by id
    public Invoice getInvoiceById(long id){
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
    }


    // make update by id
    public Invoice updateInvoice(Long id, InvoiceDTO dto) {

        // 1- التأكد أن الفاتورة موجودة
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Not Found"));

        // 2- جلب المورد الجديد
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier Not Found"));

        // 3- تحديث بيانات الفاتورة
        invoice.setInvoiceNumber(dto.getInvoiceNubmer());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setSupplier(supplier);
        invoice.setNotes(dto.getNotes());
        invoice.setUpdateAt(LocalDateTime.now());

        // 4- حذف الأصناف القديمة
        invoice.getItems().clear();

        double totalBefore = 0;
        double totalVat = 0;

        List<Invoice_Item> items = new ArrayList<>();

        // 5- إضافة الأصناف الجديدة
        for (InvoiceItemDTO itemDto : dto.getItems()) {

            Invoice_Item invoiceItem = new Invoice_Item();

            invoiceItem.setItemName(itemDto.getItemName());
            invoiceItem.setQuantity(itemDto.getQuantity());
            invoiceItem.setPriceBeforeVat(itemDto.getPriceBeforeVat());

            double vatPercent = itemDto.getVatPercent() != null
                    ? itemDto.getVatPercent()
                    : DEFUALT_VAT_PERCENT;

            invoiceItem.setVatPercent(vatPercent);

            double itemVat =
                    itemDto.getQuantity()
                            * itemDto.getPriceBeforeVat()
                            * vatPercent / 100;

            double itemTotal =
                    itemDto.getQuantity()
                            * itemDto.getPriceBeforeVat()
                            + itemVat;

            invoiceItem.setVatAmount(itemVat);
            invoiceItem.setTotalAmount(itemTotal);

            invoiceItem.setInvoice(invoice);

            items.add(invoiceItem);

            totalBefore += itemDto.getQuantity() * itemDto.getPriceBeforeVat();
            totalVat += itemVat;
        }

        // 6- ربط الأصناف الجديدة
        invoice.setItems(items);

        // 7- تحديث الإجماليات
        invoice.setTotalBeforeVat(totalBefore);
        invoice.setVatAmount(totalVat);
        invoice.setTotalAfterVat(totalBefore + totalVat);

        // 8- حفظ التعديلات
        return invoiceRepository.save(invoice);
    }

}
