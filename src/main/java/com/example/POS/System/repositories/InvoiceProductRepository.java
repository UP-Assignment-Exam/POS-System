package com.example.POS.System.repositories;

import com.example.POS.System.models.Invoice;
import com.example.POS.System.models.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Integer> {

}

