package com.example.POS.System.servicesImp;

import com.example.POS.System.DTO.CategoryDTO;
import com.example.POS.System.enums.CategoryType;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Category;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.CategoryServices;
import com.example.POS.System.utils.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImp implements CategoryServices {

    @Autowired
    private GlobalRepository db;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getListPagination(int pageNo, int pageSize) {
        Page<Category> categories = db.categories.findAllByOrderByNameDesc(PageRequest.of(pageNo - 1, pageSize));
        List<CategoryDTO> categoriesDTO = modelMapper.map(categories.getContent(), new TypeToken<List<CategoryDTO>>() {
        }.getType());

        return Utils.Success("Successfully", categoriesDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getListByType(CategoryType type) {
        List<Category> categories = db.categories.findAllByType(type);
        List<CategoryDTO> categoriesDTO = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
        }.getType());

        return Utils.Success("Successfully", categoriesDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getList() {
        List<Category> categories = db.categories.findAll();
        List<CategoryDTO> categoriesDTO = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
        }.getType());

        return Utils.Success("Successfully", categoriesDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<CategoryDTO>> getCategory(Integer id) {
        Category category = db.categories.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found!"));

        CategoryDTO categoriesDTO = modelMapper.map(category, CategoryDTO.class);

        return Utils.Success("Successfully", categoriesDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<CategoryDTO>> addCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);

        Category result = db.categories.save(category);

        categoryDTO.setId(result.getId());

        return Utils.CreateSuccess("Created Successfully", categoryDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<CategoryDTO>> removeCategory(Integer id) {
        Category category = db.categories.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found!"));

        db.categories.deleteById(category.getId());

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Utils.Success("Deleted successfully", categoryDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<CategoryDTO>> updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category category = db.categories.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found!"));

        BeanUtils.copyProperties(categoryDTO, category, Utils.getNullPropertyNames(categoryDTO));

        Category updatedCategory = db.categories.save(category);

        categoryDTO = modelMapper.map(updatedCategory, CategoryDTO.class);

        return Utils.Success("Updated Successfully", categoryDTO);
    }
}
