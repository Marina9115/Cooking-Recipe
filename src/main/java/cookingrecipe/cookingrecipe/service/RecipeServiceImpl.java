package cookingrecipe.cookingrecipe.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cookingrecipe.cookingrecipe.dao.RecipeRepository;
import cookingrecipe.cookingrecipe.dao.UserRepository;
import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.model.Recipe;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
	private RecipeRepository recipeRepo;
	private UserRepository userRepo;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepo, UserRepository userRepo) {
		this.recipeRepo = recipeRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Recipe getRecipeById(Long id) throws EntityDoesNotExistException {
		return recipeRepo.findById(id)
				.orElseThrow(() -> new EntityDoesNotExistException("Recipe with ID: " + id + " does not exist"));
	}

	@Override
	public List<Recipe> getAllRecipes() {
		return recipeRepo.findAll();
	}

	@Override
	public Recipe createRecipe(Recipe recipe, Long autorId) throws EntityExistsException, EntityDoesNotExistException {
		User user=userRepo.findById(autorId).orElseThrow(()->new EntityDoesNotExistException("User with ID: "+autorId+"does not exist.")); 
		 		if(recipe.getId()!=null&&recipeRepo.findById(recipe.getId())!=null) 
		 			throw new EntityExistsException("A recipe with the same ID already exists"); 
		 		user.getRecipes().add(recipe); 
		 		recipe.setAuthor(user); 
		 		userRepo.save(user); 
		 		return recipeRepo.save(recipe); 
		 	} 


	@Override
	public Recipe deleteRecipeById(Long id) throws EntityDoesNotExistException {
		Recipe recipeToBeDeleted = getRecipeById(id);
		recipeRepo.deleteById(id);
		return recipeToBeDeleted;
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) throws EntityDoesNotExistException {
		getRecipeById(recipe.getId());
		return recipeRepo.save(recipe);
	}




 	@Override 
 	public List<Recipe> getRecipesByUserId(Long authorId) throws EntityDoesNotExistException { 
 		User user = userRepo.findById(authorId).orElseThrow(()->new EntityDoesNotExistException("User with ID:"+authorId+" does not exist.")); 
 		return new ArrayList<Recipe>(user.getRecipes()); 
 	}

	@Override
	public void save(Recipe theRecipe) {
		
			recipeRepo.save(theRecipe);
		}
}
