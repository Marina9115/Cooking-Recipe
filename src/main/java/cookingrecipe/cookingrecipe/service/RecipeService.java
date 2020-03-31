package cookingrecipe.cookingrecipe.service;

import java.util.List;



import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.model.Recipe;

public interface RecipeService {
	
    Recipe getRecipeById(Long id) throws EntityDoesNotExistException; 
 	List<Recipe> getAllRecipes(); 
 	Recipe createRecipe(Recipe recipe, Long autorId) throws EntityExistsException, EntityDoesNotExistException; 
 	Recipe deleteRecipeById(Long id) throws EntityDoesNotExistException; 
 	Recipe updateRecipe(Recipe recipe) throws EntityDoesNotExistException; 
 	List<Recipe> getRecipesByUserId(Long authorId) throws EntityDoesNotExistException;
 	public  void save(Recipe theRecipe) ;
	
		
		
	}
 
 



