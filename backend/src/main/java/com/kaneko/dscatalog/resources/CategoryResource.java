package com.kaneko.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaneko.dscatalog.entities.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
	
	@GetMapping
	public ResponseEntity<List<Category>> findByAll(){
		List<Category> listCategory = new ArrayList<>();
		listCategory.add(new Category(1L, "Books"));
		listCategory.add(new Category(2L, "Eletronics"));
		
		return ResponseEntity.ok().body(listCategory);
	}

}
