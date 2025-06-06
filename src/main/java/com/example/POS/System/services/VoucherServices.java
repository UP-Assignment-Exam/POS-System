package com.example.POS.System.services;

import com.example.POS.System.DTO.VoucherDTO;
import com.example.POS.System.models.Product;
import com.example.POS.System.models.Voucher;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoucherServices {
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getActive();
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getList();
    public ResponseEntity<GlobalResponse<VoucherDTO>> getOne(Integer id);
    public ResponseEntity<GlobalResponse<VoucherDTO>> create(VoucherDTO voucher);
    public ResponseEntity<GlobalResponse<VoucherDTO>> delete(Integer id);
    public ResponseEntity<GlobalResponse<VoucherDTO>> update(Integer id, VoucherDTO voucher);
}
