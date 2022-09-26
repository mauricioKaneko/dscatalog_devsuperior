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

import com.kaneko.dscatalog.dto.ProductDTO;
import com.kaneko.dscatalog.entities.Product;
import com.kaneko.dscatalog.repositories.ProductRepository;
import com.kaneko.dscatalog.services.exceptions.DataBaseException;
import com.kaneko.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageResquest) {
		Page<Product> list = productRepository.findAll(pageResquest);

		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product product = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new ProductDTO(product,product.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		Product product = new Product();
		//product.setName(productDTO.getName());

		product = productRepository.save(product);

		return new ProductDTO(product);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO productDTO) {
		try {
			Product product = productRepository.getReferenceById(id);
			
//			product.setName(productDTO.getName());
			product = productRepository.save(product);
			return new ProductDTO(product);

		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not Found: " + id);
		}

		
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not Found: " + id);
		}catch (DataIntegrityViolationException ex) {
			throw new DataBaseException("Integrity Violation");
		}
		
	}
}