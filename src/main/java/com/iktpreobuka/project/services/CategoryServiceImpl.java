package com.iktpreobuka.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void deleteCategory(Integer categoryId) {
		// Prvo proverite da li kategorija postoji pre brisanja
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
		} else {
			throw new EntityNotFoundException("Kategorija sa ID " + categoryId + " ne postoji.");
		}
	}

}
