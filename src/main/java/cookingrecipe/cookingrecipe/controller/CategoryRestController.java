package cookingrecipe.cookingrecipe.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
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
import cookingrecipe.cookingrecipe.model.Category;
import cookingrecipe.cookingrecipe.service.CategoryService;

@RestController 
 @RequestMapping("/api/category/") 
 @CrossOrigin(origins = "http://localhost:3000/api/category/", maxAge = 3600) 
 public class CategoryRestController { 
	
 	private CategoryService categoryService; 
 
 	@Autowired 
 	public CategoryRestController(CategoryService categoryService) { 
 		this.categoryService = categoryService; 
 	} 
 
 
 	@GetMapping 
 	public List<Category> getAllCategories() { 
 		return categoryService.getAllCategories(); 
 	} 
 
 
 	@GetMapping("{id}") 
 	public Category getCategoryById(@PathVariable Long id) throws EntityDoesNotExistException { 
 		return categoryService.getCategoryById(id); 
 	} 
 	@PostMapping("filter/{authorId}") 
 	 	public ResponseEntity<Category> addCategory(@RequestBody Category category, @PathVariable Long authorId) throws EntityExistsException, EntityDoesNotExistException { 
 	 		if (category.getId() != null) { 
 	 			try { 
 	 				getCategoryById(category.getId()); 
 	 				throw new EntityExistsException("User with ID: " + category.getId() + " already exists"); 
 	 			} catch (EntityDoesNotExistException e) { 
 	 			} 
 	 		} 
 	 		Category categoryAdded = categoryService.addCategory(category, authorId); 
 	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}") 
 	 				.buildAndExpand(categoryAdded.getId()).toUri(); 
 	 		return ResponseEntity.created(location).body(categoryAdded); 
 	 	} 
 	@DeleteMapping("{id}") 
 	 	public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) throws EntityDoesNotExistException { 
 	 		Category categoryToBeDeleted = getCategoryById(id); 
 	 		categoryService.deleteCategoryById(id); 
 	 		return ResponseEntity.accepted().body(categoryToBeDeleted); 
 	 	} 
 	 
 	 
 	 	@PutMapping("{id}") 
 	 	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) 
 	 			throws InvalidRequestException, EntityDoesNotExistException { 
 	 		if (!id.equals(category.getId())) 
 	 			throw new InvalidRequestException("ID(" + id + ") and user ID(" + category.getId() + ") don't match."); 
 	 		return ResponseEntity.accepted().body(categoryService.updateCategody(category)); 
 	 	} 
 	 } 

 	