package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.InvoiceDTO;
import com.example.POS.System.DTO.InvoiceProductsDTO;
import com.example.POS.System.exceptions.Custom.BadRequestException;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.*;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.InvoicesServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicesServicesImp implements InvoicesServices {
    @Autowired
    private GlobalRepository db;

    @Override
    public ResponseEntity<GlobalResponse<List<InvoiceDTO>>> getList(int pageNo, int pageSize) {
        Page<Invoice> data = db.invoices.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNo - 1, pageSize));

        List<InvoiceDTO> dataDTO = db.mapper.map(data.getContent(), new TypeToken<List<InvoiceDTO>>() {}.getType());

        return Utils.Success("Successfully", dataDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<InvoiceDTO>> getOne(Integer id) {
        Invoice invoice = db.invoices.findById(id).orElseThrow(() -> new NotFoundException("Invoice not found!"));

        InvoiceDTO invoiceDTO = db.mapper.map(invoice, InvoiceDTO.class);

        return Utils.Success("Successfully", invoiceDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<Invoice>> create(InvoiceDTO request) {
        User user = db.users.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User Not Found"));

        Invoice invoice = new Invoice();

        invoice.setUser(user);

        List<InvoiceProduct> products = new ArrayList<>(List.of());
        for (int i = 0; i < request.getInvoiceProducts().size(); i++) {
            InvoiceProductsDTO requestProduct = request.getInvoiceProducts().get(i);
            Product product = db.products.findById(requestProduct.getProductId()).orElseThrow(() -> new NotFoundException("Product " + requestProduct.getProductId() + " not found"));

            if (requestProduct.getQuantity() > product.getQuantity()) {
                throw new BadRequestException("Requested quantity exceeds available stock. Available quantity: " + product.getQuantity());
            }

            InvoiceProduct invoiceProduct = new InvoiceProduct();
            invoiceProduct.setSize(requestProduct.getSize());
            invoiceProduct.setColor(requestProduct.getColor());
            invoiceProduct.setQuantity(requestProduct.getQuantity());

            Double productPrice = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);

            invoice.setTotalPrice(invoice.getTotalPrice() + productPrice * requestProduct.getQuantity());

            invoiceProduct.setProduct(product);
            products.add(invoiceProduct);
        }
        invoice.setInvoiceProducts(products);
        invoice.setTotalItem(products.size());

        if (request.getVoucherId() != null) {
            Voucher voucher = db.vouchers.findById(request.getVoucherId()).orElseThrow(() -> new NotFoundException("Voucher not found"));
            invoice.setVoucher(voucher);
        }

        db.invoices.save(invoice);

        for (InvoiceProduct product : products) {
            product.setInvoice(invoice);
            db.invoiceProducts.save(product);
        }

        return Utils.Success("Make an order Successfully", null);
    }

    @Override
    public ResponseEntity<GlobalResponse<Invoice>> delete(Integer id) {
        return null;
//        Product product = db.products.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found!"));
//
//        Invoice response = db.mapper.map(product, Invoice.class);
//
//        db.products.deleteById(product.getId());
//
//        return Utils.Success("Deleted successfully", response);
    }
}
