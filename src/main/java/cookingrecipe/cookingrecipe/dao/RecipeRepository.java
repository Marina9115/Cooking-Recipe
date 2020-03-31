package cookingrecipe.cookingrecipe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cookingrecipe.cookingrecipe.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	
}
