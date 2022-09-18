package com.kaneko.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaneko.dscatalog.dto.CategoryDTO;
import com.kaneko.dscatalog.entities.Category;
import com.kaneko.dscatalog.repositories.CategoryRepository;
import com.kaneko.dscatalog.services.exceptions.DataBaseException;
import com.kaneko.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageResquest) {
		Page<Category> list = categoryRepository.findAll(pageResquest);

		return list.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category category = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());

		category = categoryRepository.save(category);

		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
		try {
			Category category = categoryRepository.getReferenceById(id);
			
			category.setName(categoryDTO.getName());
			category = categoryRepository.save(category);
			return new CategoryDTO(category);

		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not Found: " + id);
		}

		
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not Found: " + id);
		}catch (DataIntegrityViolationException ex) {
			throw new DataBaseException("Integrity Violation");
		}
		
	}
}
