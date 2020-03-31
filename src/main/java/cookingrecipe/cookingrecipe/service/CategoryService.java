package cookingrecipe.cookingrecipe.service;

import java.util.List;



import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.model.Category;

public interface CategoryService {
	
	    List<Category> getAllCategories(); 
	 	Category getCategoryById(Long id) throws EntityDoesNotExistException; 
	 	Category addCategory(Category category, Long authorId) throws EntityExistsException, EntityDoesNotExistException; 
	 	Category deleteCategoryById(Long id) throws EntityDoesNotExistException; 
	 	Category updateCategody(Category category) throws EntityDoesNotExistException;
		void save(Category theCategory); 


}
