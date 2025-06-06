package com.example.POS.System.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvoiceProductsDTO {
    private ProductDTO product;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer productId;
    private Integer quantity;
    private String color;
    private String size;
}
