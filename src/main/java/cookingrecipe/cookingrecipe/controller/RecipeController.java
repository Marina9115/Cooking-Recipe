package cookingrecipe.cookingrecipe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;


import cookingrecipe.cookingrecipe.model.Recipe;
import cookingrecipe.cookingrecipe.service.CategoryService;
import cookingrecipe.cookingrecipe.service.RecipeService;
import cookingrecipe.cookingrecipe.service.UserService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	private UserService userService;
	private CategoryService categoryService;
	private RecipeService recipeService;

	@Autowired
	public RecipeController(UserService userService, CategoryService categoryService, RecipeService recipeService) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.recipeService = recipeService;
	}
	

	// load employee data
	
		private List<Recipe> theRecipe;
		
		@PostConstruct
		private void loadData() {
			
			// create employees
			Recipe r1 = new Recipe();
			Recipe er2 = new Recipe(null, null, null, 2, "Emma", "Baumgarten", "emma@luv2code.com", null, null, null);
			

			// create the list
			theRecipe = new ArrayList<>();
			
			// add to the list
			theRecipe.add(r1);
			theRecipe.add(er2);
			
		
		}
		
		// add mapping for "/list"

		@GetMapping("/recipe-list")
		public String listRecipes(Model theModel) {
			
			// add to the spring model
			theModel.addAttribute("recipes", theRecipe);
			
			return "recipe-list";
		}
	

	@GetMapping
	private String getRecipeList(Model model) {
		model.addAttribute("recipe", recipeService.getAllRecipes());
		return "recipe-list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Recipe theRecipe = new Recipe();
		
		theModel.addAttribute("Recipe", theRecipe);
		
		return "recipe/recipe-form";
	}
}
//	
//  public String saveRecipe(@ModelAttribute("recipe") Recipe theRecipe) {
//		
//		// save the employee
//		recipeService.save(theRecipe);
//		
//		// use a redirect to prevent duplicate submissions
//		return "redirect:/recipe-list";
//	}
//	
//}


