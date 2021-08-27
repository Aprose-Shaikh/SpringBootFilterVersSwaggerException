package com.aci.payon.rest.aprosewebservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 
@RestController
public class UserJPAResource {
	@Autowired
	UserDaoService userDaoService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> getAllUser() {
		return userRepository.findAll(); 
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable String id) {
		Optional<User> user = userRepository.findById(id); 
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		EntityModel<User> resource = EntityModel.of(user.get());//new EntityModel<User>(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}
	
	@GetMapping(path = "/jpa/users/{id}/post")
	public List<Post> getUserPost(@PathVariable String id) {
		Optional<User> user = userRepository.findById(id); 
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		/*
		 * user.get().getPosts() EntityModel<User> resource =
		 * EntityModel.of(user.get().getPosts());//new EntityModel<User>(user.get());
		 * 
		 * WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());
		 * 
		 * resource.add(linkTo.withRel("all-users"));
		 */

		// HATEOAS

		return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/post")
	public ResponseEntity<Post> createPost(@PathVariable String id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id); 
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getPostId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	@PostMapping (path = "/jpa/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable String id) {
		User user = userDaoService.deleteById(id); 
		if(user == null) {
			throw new UserNotFoundException("User Not Found");
		}
	}
	
}
