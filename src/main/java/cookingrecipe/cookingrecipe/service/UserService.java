package cookingrecipe.cookingrecipe.service;



import java.util.List;


import org.springframework.security.core.userdetails.UserDetailsService;
import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.model.User;

public interface UserService extends UserDetailsService { 
	 	User getUserById(Long id) throws EntityDoesNotExistException; 
	 	List<User> getAllUsers(); 
	 	User getUsersByUsername(String userName); 
	 	User deleteUserById(Long id) throws EntityDoesNotExistException; 
	 	User addHomeCook(User user) throws EntityExistsException;
	 	User updateUser(User user) throws EntityDoesNotExistException;
		List<User> findByRole(String role);
		  

		
		
	 
	 


}
