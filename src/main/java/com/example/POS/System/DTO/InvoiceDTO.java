package com.example.POS.System.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class InvoiceDTO {
    private Double totalPrice;
    private Integer totalItem;
    private List<InvoiceProductsDTO> invoiceProducts;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer voucherId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userId;
    private UserDTO user;
    private VoucherDTO voucher;
}