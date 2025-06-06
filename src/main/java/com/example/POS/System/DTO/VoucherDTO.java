package com.example.POS.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoucherDTO {
    private Integer id;
    private String name;
    private Double discount;
    private String description;
    private String image;
    private LocalDate expiredDate;
    private LocalDate startDate;
    private Boolean status;
}
