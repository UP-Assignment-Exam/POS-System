package com.example.POS.System.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is Required!")
    private String name;
    @NotNull(message = "Discount is Required!")
    private Double discount;
    @NotEmpty(message = "Description is Required!")
    private String description;

    @NotEmpty(message = "Please input Image")
    private String image;

    @NotNull(message = "Please input a Expired Date")
    private LocalDate expiredDate;

    @NotNull(message = "Please input a Start Date")
    private LocalDate startDate;

    private Boolean status = false;

    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;
}
