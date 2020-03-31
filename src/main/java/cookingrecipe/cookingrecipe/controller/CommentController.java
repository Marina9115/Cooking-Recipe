package cookingrecipe.cookingrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cookingrecipe.cookingrecipe.model.Category;
import cookingrecipe.cookingrecipe.model.Comment;
import cookingrecipe.cookingrecipe.service.CategoryService;
import cookingrecipe.cookingrecipe.service.CommentService;
import cookingrecipe.cookingrecipe.service.RecipeService;
import cookingrecipe.cookingrecipe.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private UserService userService;
	private CategoryService categoryService;
	private RecipeService recipeService;
	private CommentService commentService;
	

	@Autowired
	public CommentController(UserService userService, CategoryService categoryService,  RecipeService recipeService,
	 CommentService commentService) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.commentService=commentService;
		this.recipeService=recipeService;
		
		
	}

	@GetMapping
	private String getCategoryList(Model model) {
		model.addAttribute("recipe", categoryService.getAllCategories());
		return "category-list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Comment theComment = new Comment();
		
		theModel.addAttribute("Comment", theComment);
		
		return "Comment/Comment-form";
	}
	
  public String saveComment(@ModelAttribute("Comment") Comment theComment) {
		
		// save the employee
	  commentService.save(theComment);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/comment-list";
	}
	
}


