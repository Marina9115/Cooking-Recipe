package cookingrecipe.cookingrecipe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cookingrecipe.cookingrecipe.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByAuthority(String string);

	List<Role> findAll();

	

}
