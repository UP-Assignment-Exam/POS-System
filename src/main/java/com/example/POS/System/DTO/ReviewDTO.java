package com.example.POS.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {

    private Integer id;
    private UserDTO user;
    private Integer productId;
    private Integer userId;
    private String comment;
    private Integer star;
}
