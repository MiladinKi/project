package com.iktpreobuka.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	public UserEntity findByUsername(String username);

}
