package com.example.POS.System.repositories;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalRepository {
    @Autowired
    public CategoryRepository categories;
    @Autowired
    public InvoiceRepository invoices;
    @Autowired
    public InvoiceProductRepository invoiceProducts;
    @Autowired
    public ProductRepository products;
    @Autowired
    public RoleRepository roles;
    @Autowired
    public VoucherRepository vouchers;
    @Autowired
    public UserRepository users;
    @Autowired
    public VerifySecretCodeRepository verifySecretCodes;
    @Autowired
    public ReviewRepository reviews;
    @Autowired
    public ModelMapper mapper;
}
