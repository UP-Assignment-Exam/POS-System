package com.example.POS.System.controllers;

import com.example.POS.System.DTO.VoucherDTO;
import com.example.POS.System.services.VoucherServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/vouchers")
public class VoucherController {
    @Autowired
    private VoucherServices voucherServices;

    @GetMapping("/active")
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getActive() {
        return voucherServices.getActive();
    }

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getAll() {
        return voucherServices.getList();
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<VoucherDTO>> create(@RequestBody VoucherDTO request) {
        return voucherServices.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<VoucherDTO>> update(@PathVariable Integer id, @RequestBody VoucherDTO role) {
        return voucherServices.update(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<VoucherDTO>> delete(@PathVariable Integer id) {
        return voucherServices.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<VoucherDTO>> getOne(@PathVariable Integer id) {
        return voucherServices.getOne(id);
    }
}
