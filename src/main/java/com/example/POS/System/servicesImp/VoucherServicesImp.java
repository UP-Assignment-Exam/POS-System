package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.VoucherDTO;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Voucher;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.VoucherServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServicesImp implements VoucherServices {

    @Autowired
    private GlobalRepository db;

    @Override
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getActive() {
        List<Voucher> vouchers = db.vouchers.findActiveVouchers();
        List<VoucherDTO> vouchersDTO = db.mapper.map(vouchers, new TypeToken<List<VoucherDTO>>() {
        }.getType());

        return Utils.Success("Successfully", vouchersDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<VoucherDTO>>> getList() {
        List<Voucher> vouchers = db.vouchers.findAll();
        List<VoucherDTO> vouchersDTO = db.mapper.map(vouchers, new TypeToken<List<VoucherDTO>>() {
        }.getType());

        return Utils.Success("Successfully", vouchersDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<VoucherDTO>> getOne(Integer id) {
        Voucher voucher = db.vouchers.findById(id).orElseThrow(() -> new NotFoundException("Vouchers Not Found!"));

        VoucherDTO response = db.mapper.map(voucher, VoucherDTO.class);

        return Utils.Success("Successfully", response);
    }

    @Override
    public ResponseEntity<GlobalResponse<VoucherDTO>> create(VoucherDTO request) {
        Voucher voucher = db.mapper.map(request, Voucher.class);

        Voucher result = db.vouchers.save(voucher);

        request.setId(result.getId());

        return Utils.CreateSuccess("Created Successfully", request);
    }

    @Override
    public ResponseEntity<GlobalResponse<VoucherDTO>> delete(Integer id) {
        Voucher voucher = db.vouchers.findById(id).orElseThrow(() -> new NotFoundException("Voucher Not Found!"));

        VoucherDTO categoryDTO = db.mapper.map(voucher, VoucherDTO.class);

        db.vouchers.deleteById(voucher.getId());

        return Utils.Success("Deleted successfully", categoryDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<VoucherDTO>> update(Integer id, VoucherDTO request) {
        Voucher voucher = db.vouchers.findById(id).orElseThrow(() -> new NotFoundException("Voucher Not Found!"));

        BeanUtils.copyProperties(request, voucher, Utils.getNullPropertyNames(request));

        Voucher updatedCategory = db.vouchers.save(voucher);

        request = db.mapper.map(updatedCategory, VoucherDTO.class);

        return Utils.Success("Updated Successfully", request);
    }
}
