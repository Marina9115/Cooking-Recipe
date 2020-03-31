package cookingrecipe.cookingrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cookingrecipe.cookingrecipe.model.Category;
import cookingrecipe.cookingrecipe.service.CategoryService;
import cookingrecipe.cookingrecipe.service.UserService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private UserService userService;
	private CategoryService categoryService;
	

	@Autowired
	public CategoryController(UserService userService, CategoryService categoryService) {
		this.userService = userService;
		this.categoryService = categoryService;
		
	}

	@GetMapping
	private String getCategoryList(Model model) {
		model.addAttribute("recipe", categoryService.getAllCategories());
		return "category-list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Category theCategory = new Category();
		
		theModel.addAttribute("Category", theCategory);
		
		return "category/category-form";
	}
	
  public String saveCategory(@ModelAttribute("category") Category theCategory) {
		
		// save the employee
		categoryService.save(theCategory);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/category-list";
	}
	
}


