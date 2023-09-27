package com.iktpreobuka.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

}
