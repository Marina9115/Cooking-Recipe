package cookingrecipe.cookingrecipe.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import cookingrecipe.cookingrecipe.exception.InvalidRequestException;
import cookingrecipe.cookingrecipe.model.Recipe;
import cookingrecipe.cookingrecipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/recipe/")
@CrossOrigin(origins = "http://localhost:8080/api/recipe/", maxAge = 3600)
public class RecipeRestController {
	private RecipeService recipeService;

	@Autowired
	public RecipeRestController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
//
//	@GetMapping
//	public List<Recipe> getAllRecipes() {
//		return recipeService.getAllRecipes();
//	}

	@GetMapping("{id}")
	public Recipe getRecipeById(@PathVariable Long id) throws EntityDoesNotExistException {
		return recipeService.getRecipeById(id);
	}

	@GetMapping("filtered/{authorId}")
	public List<Recipe> getRecipesByAuthorId(@PathVariable Long authorId) throws EntityDoesNotExistException {
		return recipeService.getRecipesByUserId(authorId);
	}

	@PostMapping("{authorId}")
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe, @PathVariable Long authorId)
			throws EntityExistsException, EntityDoesNotExistException {
		if (recipe.getId() != null) {
			try {
				getRecipeById(recipe.getId());
				throw new EntityExistsException("Recipe with ID: " + recipe.getId() + " already exists");
			} catch (EntityDoesNotExistException e) {
			}
		}
		Recipe recipeAdded = recipeService.createRecipe(recipe, authorId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
				.buildAndExpand(recipeAdded.getId()).toUri();
		return ResponseEntity.created(location).body(recipeAdded);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Recipe> deleteRecipeById(@PathVariable Long id) throws EntityDoesNotExistException {
		Recipe recipeToBeDeleted = getRecipeById(id);
		recipeService.deleteRecipeById(id);
		return ResponseEntity.accepted().body(recipeToBeDeleted);
	}

	@PutMapping("{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe)
			throws InvalidRequestException, EntityDoesNotExistException {
		if (!id.equals(recipe.getId()))
			throw new InvalidRequestException("ID(" + id + ") and recipe ID(" + recipe.getId() + ") don't match.");
		return ResponseEntity.accepted().body(recipeService.updateRecipe(recipe));
	}
	@GetMapping
	private String getRecipeList(Model model) {
		model.addAttribute("recipe", recipeService.getAllRecipes());
		return "recipe-list";
	}
}



