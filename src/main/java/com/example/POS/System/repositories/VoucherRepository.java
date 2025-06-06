package com.example.POS.System.repositories;

import com.example.POS.System.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    @Query("SELECT v FROM Voucher v WHERE v.expiredDate >= CURRENT_DATE AND v.startDate <= CURRENT_DATE AND v.status = true")
    List<Voucher> findActiveVouchers();
}

