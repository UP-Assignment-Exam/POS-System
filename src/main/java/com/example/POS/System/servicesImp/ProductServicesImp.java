package com.example.POS.System.servicesImp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.POS.System.DTO.ProductDTO;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Category;
import com.example.POS.System.models.Product;
import com.example.POS.System.models.User;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.security.CustomUserDetailsService;
import com.example.POS.System.services.ProductServices;
import com.example.POS.System.utils.GlobalResponse;

@Service
public class ProductServicesImp implements ProductServices {

    @Autowired
    private GlobalRepository db;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listByCategoryId(Integer categoryId) {
        List<Product> products = db.products.findByCategoryId(categoryId);
        List<ProductDTO> productsDTO = mapProduct(products);
        return Utils.Success("Successfully", productsDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listFavorite(Integer userId, Integer categoryId, String sortOrder, String name) {
        User user = db.users.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found!"));

        List<Product> favoriteProducts = new ArrayList<>(user.getFavoriteProducts());

        if (categoryId != null) {
            favoriteProducts.removeIf(product -> !product.getCategory().getId().equals(categoryId));
        }

        if (name != null && !name.trim().isEmpty()) {
            favoriteProducts.removeIf(product -> !product.getName().toLowerCase().contains(name.toLowerCase()));
        }

        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                favoriteProducts.sort(Comparator.comparing(Product::getPrice));
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                favoriteProducts.sort(Comparator.comparing(Product::getPrice).reversed());
            }
        }

        List<ProductDTO> favProductsDTO = mapProduct(favoriteProducts);

        return Utils.Success("Successfully", favProductsDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> addFavorite(Integer userId, Integer productId) {
        User user = db.users.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found!"));
        Product product = db.products.findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found!"));

        if (user.getFavoriteProducts().contains(product)) {
            return Utils.BadRequest("Product is already in the favorites.");
        }

        user.getFavoriteProducts().add(product);
        db.users.save(user);
        return Utils.Success("Product added to favorites successfully.", null);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> removeFavorite(Integer userId, Integer productId) {
        User user = db.users.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found!"));
        Product product = db.products.findById(productId).orElseThrow(() -> new NotFoundException("Products Not Found!"));

        if (user.getFavoriteProducts().contains(product)) {
            user.getFavoriteProducts().remove(product);
            db.users.save(user);
            return Utils.Success("Product removed from favorites successfully.", null);
        } else {
            return Utils.BadRequest("Product is not in the favorites.");
        }
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getListPagination(int pageNo, int pageSize) {
        Page<Product> list = db.products.findAllByOrderByNameDesc(PageRequest.of(pageNo - 1, pageSize));
        List<ProductDTO> listDTO = mapProduct(list.getContent());

        return Utils.Success("Successfully", listDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getSale() {
        List<Product> products = db.products.findAllOrderBySales();

        List<ProductDTO> productsDTO = mapProduct(products);

        return Utils.Success("Successfully", productsDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getLatest() {
        List<Product> products = db.products.findTop10ByOrderByCreatedAtDesc();

        List<ProductDTO> productsDTO = mapProduct(products);

        return Utils.Success("Successfully", productsDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getList() {
        List<Product> products = db.products.findAll();

        List<ProductDTO> productsDTO = mapProduct(products);

        return Utils.Success("Successfully", productsDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<ProductDTO>> getOne(Integer id) {
        Product product = db.products.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found!"));

        ProductDTO response = db.mapper.map(product, ProductDTO.class);

        return Utils.Success("Successfully", response);
    }

    @Override
    public ResponseEntity<GlobalResponse<ProductDTO>> create(ProductDTO request) {
        Category category = db.categories.findById(request.getCategoryId()).orElseThrow(() -> new NotFoundException("Category Not Found!"));

        Product product = db.mapper.map(request, Product.class);
        product.setCategory(category);

        Product result = db.products.save(product);

        request.setId(result.getId());

        return Utils.CreateSuccess("Created Successfully", request);
    }

    @Override
    public ResponseEntity<GlobalResponse<ProductDTO>> delete(Integer id) {
        Product product = db.products.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found!"));

        ProductDTO response = db.mapper.map(product, ProductDTO.class);

        db.products.deleteById(product.getId());

        return Utils.Success("Deleted successfully", response);
    }

    @Override
    public ResponseEntity<GlobalResponse<ProductDTO>> update(Integer id, ProductDTO request) {
        Product product = db.products.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found!"));

        BeanUtils.copyProperties(request, product, Utils.getNullPropertyNames(request));

        Product updatedCategory = db.products.save(product);

        ProductDTO response = db.mapper.map(updatedCategory, ProductDTO.class);

        return Utils.Success("Updated Successfully", response);
    }

    public List<ProductDTO> mapProduct(List<Product> products) {
        User currentUser = Utils.getUserFromAuth(db);

        return products.stream().map(product -> {
            ProductDTO productDTO = db.mapper.map(product, ProductDTO.class);

            if (currentUser != null && product.getFavoredByUsers().contains(currentUser)) {
                productDTO.setIsFavorite(true);
            } else {
                productDTO.setIsFavorite(false);
            }

            return productDTO;
        }).collect(Collectors.toList());
    }
}
