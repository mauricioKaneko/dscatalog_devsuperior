package com.kaneko.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaneko.dscatalog.dto.CategoryDTO;
import com.kaneko.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findByAll() {
		List<CategoryDTO> listCategory = categoryService.findAll();

		return ResponseEntity.ok().body(listCategory);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO categoryDto = categoryService.findById(id);

		return ResponseEntity.ok().body(categoryDto);
	}

}
