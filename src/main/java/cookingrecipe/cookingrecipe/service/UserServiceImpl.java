package cookingrecipe.cookingrecipe.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cookingrecipe.cookingrecipe.dao.RoleRepository;
import cookingrecipe.cookingrecipe.dao.UserRepository;
import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.model.Role;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder encoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.encoder = encoder;
		if (roleRepo.findAll().size() != 2) {
			roleRepo.save(new Role("ROLE_Administrator"));
			roleRepo.save(new Role("ROLE_HomeCook"));
		}
	}

	@Override
	public User getUserById(Long id) throws EntityDoesNotExistException {
		return userRepo.findById(id)
				.orElseThrow(() -> new EntityDoesNotExistException("User with id: " + id + " does not exist!"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUsersByUsername(String userName) {
		return userRepo.findUserByUsername(userName).orElse(null);
	}

	@Override
	public User deleteUserById(Long id) throws EntityDoesNotExistException {
		User userToBeDeleted = getUserById(id);
		userRepo.deleteById(id);
		return userToBeDeleted;
	}


	@Override
	public User addHomeCook(User user) throws EntityExistsException {
		if (user.getId() != null && userRepo.findById(user.getId()) != null)
			throw new EntityExistsException("User with same ID already exists");
		Role role = roleRepo.findByAuthority("ROLE_HomeCook");
		user.getRoles().add(role);
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findUserByUsername(username).orElse(null);
		return user;
	}

	@Override
	public User updateUser(User user) throws EntityDoesNotExistException {
		getUserById(user.getId());
		return userRepo.save(user);
	}

	@Override
	public List<User> findByRole(String role) {
		
		return userRepo.findByRoles(roleRepo.findByAuthority("ROLE_ADMINISTRATOR"));
	}


	
	
	
			 
		 

	

}
