package com.example.POS.System.repositories;

import com.example.POS.System.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Page<Invoice> findAllByOrderByCreatedAtDesc(Pageable pageable);
}

