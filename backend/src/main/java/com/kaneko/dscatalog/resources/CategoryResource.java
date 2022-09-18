package com.kaneko.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaneko.dscatalog.entities.Category;
import com.kaneko.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findByAll(){
		List<Category> listCategory = categoryService.findAll();
		
		return ResponseEntity.ok().body(listCategory);
	}

}
