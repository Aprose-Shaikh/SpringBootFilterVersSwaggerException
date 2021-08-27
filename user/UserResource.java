package com.aci.payon.rest.aprosewebservices.restfulwebservices.user;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserResource {
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return userDaoService.findAll(); 
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable String id) {
		User user = userDaoService.find(id); 
		if(user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		EntityModel<User> resource = EntityModel.of(user);//new EntityModel<User>(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}
	
	@PostMapping (path = "/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable String id) {
		User user = userDaoService.deleteById(id); 
		if(user == null) {
			throw new UserNotFoundException("User Not Found");
		}
	}
	

}
