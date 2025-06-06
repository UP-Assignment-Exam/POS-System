package com.example.POS.System.services;

import com.example.POS.System.DTO.InvoiceDTO;
import com.example.POS.System.models.Invoice;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoicesServices {
    public ResponseEntity<GlobalResponse<List<InvoiceDTO>>> getList(int pageNo, int pageSize);
    public ResponseEntity<GlobalResponse<InvoiceDTO>> getOne(Integer id);
    public ResponseEntity<GlobalResponse<Invoice>> create(InvoiceDTO request);
    public ResponseEntity<GlobalResponse<Invoice>> delete(Integer id);
}
