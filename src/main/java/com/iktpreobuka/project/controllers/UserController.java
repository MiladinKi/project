package com.iktpreobuka.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.entities.EUserRole;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repository.UserRepository;
import com.iktpreobuka.project.security.Views;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	List<UserEntity> users = new ArrayList<>();

//	private List<UserEntity> getDB() {
//		if (users.size() == 0) {
//			UserEntity u1 = new UserEntity(1, "Miladin", "Kovacevic", "batkeki", "miladin", "mk12@gmail.com",
//					EUserRole.ROLE_CUSTOMER);
//			UserEntity u2 = new UserEntity(2, "Tanja", "Krstin", "tanja", "tanja", "tanjak@yahoo.com",
//					EUserRole.ROLE_CUSTOMER);
//			UserEntity u3 = new UserEntity(3, "Dusan", "Kovacevic", "dusan", "dusan", "duskov@gmail.com",
//					EUserRole.ROLE_CUSTOMER);
//
//			users.add(u1);
//			users.add(u2);
//			users.add(u3);
//		}
//		return users;
//	}

	@RequestMapping(method = RequestMethod.GET, value = "/public")
	@JsonView(Views.Public.class)
	public List<UserEntity> getAllUsersPublic() {
//		return getDB();
		return (List<UserEntity>) userRepository.findAll();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/private")
	@JsonView(Views.Private.class)
	public List<UserEntity> getAllUsersPrivate() {
//		return getDB();
		return (List<UserEntity>) userRepository.findAll();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@JsonView(Views.Admin.class)
	public List<UserEntity> getAllUsersAdmin() {
//		return getDB();
		return (List<UserEntity>) userRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public UserEntity getUserId(@PathVariable Integer id) {
//		for (UserEntity user : getDB()) {
//			if (user.getId().equals(id)) {
//				return user;
//			}
//		}
//		return null;
		UserEntity user = userRepository.findById(id).get();
		return user;
	}

	@RequestMapping(method = RequestMethod.POST)
	public UserEntity addUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String username, @RequestParam String password, @RequestParam String email,
			@RequestParam EUserRole userRole) {
//	public UserEntity addUser(@RequestBody UserEntity user) {
		UserEntity user = new UserEntity();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserRole(userRole);
		userRepository.save(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserEntity changeUser(@RequestBody UserEntity updatedUser, @PathVariable Integer id) {
//		for (UserEntity user : getDB()) {
//			if (user.getId().equals(id)) {
		UserEntity user = userRepository.findById(id).get();
		if (user.getFirstName() != null) {
			user.setFirstName(updatedUser.getFirstName());
		}
		if (user.getLastName() != null) {
			user.setLastName(updatedUser.getLastName());
		}
		if (user.getUsername() != null) {
			user.setUsername(updatedUser.getUsername());
		}
		if (user.getEmail() != null) {
			user.setEmail(updatedUser.getEmail());
		}
		userRepository.save(user);
		return user;
//			}
//		}
//		return null;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/change/{id}/role/{role}")
	public UserEntity changeUserRole(@PathVariable Integer id, @PathVariable EUserRole role) {
//		for (UserEntity user : getDB()) {
//			if (user.getId().equals(id)) {
//				user.setUserRole(role);
//				return user;
//			}
//		}
//		return null;
		UserEntity user = userRepository.findById(id).get();
		user.setUserRole(role);
		userRepository.save(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/changePassword/{id}")
	public UserEntity changePassword(@RequestParam String oldPass, @RequestParam String newPass,
			@PathVariable Integer id) {
//		for (UserEntity user : getDB()) {
//			if (user.getId().equals(id)) {
//				if (user.getPassword().equals(oldPass)) {
//					user.setPassword(newPass);
//				}
//				return user;
//			}
//		}
//		return null;
		UserEntity user = userRepository.findById(id).get();
		if (user.getPassword().equals(oldPass)) {
			user.setPassword(newPass);
		}
		userRepository.save(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteUser(@PathVariable Integer id) {
		
//		for (UserEntity user : getDB()) {
//			if (user.getId().equals(id)) {
//				getDB().remove(user);
//				return user;
//			}
//
//		}
//		return null;
	 userRepository.deleteById(id);
	
	}

	@RequestMapping(method = RequestMethod.GET, value = "/by-username/{username}")
	public UserEntity getByUsername(@PathVariable String username) {
		UserEntity user = userRepository.findByUsername(username);

		return user;
	}
}
