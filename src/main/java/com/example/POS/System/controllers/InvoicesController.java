package com.example.POS.System.controllers;

import com.example.POS.System.DTO.InvoiceDTO;
import com.example.POS.System.models.Invoice;
import com.example.POS.System.services.InvoicesServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/invoices")
public class InvoicesController {
    @Autowired
    private InvoicesServices invoicesServices;

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<InvoiceDTO>>> getList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return invoicesServices.getList(pageNo, pageSize);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<Invoice>> create(@RequestBody InvoiceDTO request) {
        return invoicesServices.create(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Invoice>> delete(@PathVariable Integer id) {
        return invoicesServices.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<InvoiceDTO>> getOne(@PathVariable Integer id) {
        return invoicesServices.getOne(id);
    }
}
