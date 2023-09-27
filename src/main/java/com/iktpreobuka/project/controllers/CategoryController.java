package com.iktpreobuka.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.repository.CategoryRepository;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {
	List<CategoryEntity> categories = new ArrayList<>();

	@Autowired
	private CategoryRepository categoryRepository;

//	private List<CategoryEntity> getDB() {
//		if (categories.size() == 0) {
//			CategoryEntity c1 = new CategoryEntity(1, "music", "description1");
//			CategoryEntity c2 = new CategoryEntity(2, "food", "description2");
//			CategoryEntity c3 = new CategoryEntity(3, "entertainment", "description3");
//
//			categories.add(c1);
//			categories.add(c2);
//			categories.add(c3);
//
//		}
//		return categories;
//	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CategoryEntity> getAllCategories() {
		return (List<CategoryEntity>) categoryRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public CategoryEntity newCategory(@RequestParam String categoryName, @RequestParam String categoryDescription) {
		CategoryEntity category = new CategoryEntity();
		category.setCategoryName(categoryName);
		category.setCategoryDescription(categoryDescription);
		categoryRepository.save(category);
		return category;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CategoryEntity changeCategory(@RequestBody CategoryEntity updatedCategory, @PathVariable Integer id) {
		CategoryEntity category = categoryRepository.findById(id).get();

		if (updatedCategory.getCategoryName() != null) {
			category.setCategoryName(updatedCategory.getCategoryName());
		}
		if (updatedCategory.getCategoryDescription() != null) {
			category.setCategoryDescription(updatedCategory.getCategoryDescription());
		}

		categoryRepository.save(category);
		return category;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		categoryRepository.deleteById(id);

	}

	@RequestMapping(method = RequestMethod.GET, value = "findById/{id}")
	public CategoryEntity findById(@PathVariable Integer id) {
		CategoryEntity category = categoryRepository.findById(id).get();
		return category;
	}
}
