package cookingrecipe.cookingrecipe.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.exception.InvalidEntityException;
import cookingrecipe.cookingrecipe.exception.InvalidRequestException;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.service.UserService;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "http://localhost:3000/api/user/", maxAge = 3600)
public class UserRestController {
	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("{id}")
	public User getUserById(@PathVariable Long id) throws EntityDoesNotExistException {
		return userService.getUserById(id);
	}

	@GetMapping("byUsername/{username}")
	public User getUsersByUserName(@PathVariable String username) throws EntityDoesNotExistException {
		return userService.getUsersByUsername(username);
	}

	@PostMapping("homeCook/")
	public ResponseEntity<User> addHomeCook(@RequestBody User homeCook)
			throws EntityExistsException, InvalidEntityException {
		if (homeCook.getId() != null) {
			try {
				getUserById(homeCook.getId());
				throw new EntityExistsException("User with ID: " + homeCook.getId() + " already exists");
			} catch (EntityDoesNotExistException e) {
			}
		}
		User userAdded = userService.addHomeCook(homeCook);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
				.buildAndExpand(userAdded.getId()).toUri();
		return ResponseEntity.created(location).body(userAdded);
	}

	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user)
			throws InvalidRequestException, EntityDoesNotExistException {
		if (!id.equals(user.getId()))
			throw new InvalidRequestException("ID(" + id + ") and user ID(" + user.getId() + ") don't match.");
		return ResponseEntity.accepted().body(userService.updateUser(user));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) throws EntityDoesNotExistException {
		return ResponseEntity.accepted().body(userService.deleteUserById(id));
	}
}
