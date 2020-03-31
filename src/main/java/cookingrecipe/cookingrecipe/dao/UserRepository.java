package cookingrecipe.cookingrecipe.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import cookingrecipe.cookingrecipe.model.Role;
import cookingrecipe.cookingrecipe.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByUsername(String userName);
	List<User> findByRoles(Role role);
	User getUserByUsername(String username);

}
