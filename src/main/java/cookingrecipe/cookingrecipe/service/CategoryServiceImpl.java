package cookingrecipe.cookingrecipe.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cookingrecipe.cookingrecipe.dao.CategoryRepository;
import cookingrecipe.cookingrecipe.dao.UserRepository;
import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.model.Category;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepo;
	private UserRepository userRepo;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepo, UserRepository userRepo) {
		this.categoryRepo = categoryRepo;
		this.userRepo = userRepo;
	}

	@Override
	public List<Category> getAllCategories() {
              return categoryRepo.findAll();
	}

	@Override
	public Category getCategoryById(Long id) throws EntityDoesNotExistException {
		return categoryRepo.findById(id)
				.orElseThrow(() -> new EntityDoesNotExistException("Category with ID: " + id + " does not exist."));
	}


   @Override
	public Category deleteCategoryById(Long id) throws EntityDoesNotExistException {
		Category categoryToBeDeleted = getCategoryById(id);
		categoryRepo.deleteById(id);
		return categoryToBeDeleted;
	}

	@Override
	public Category updateCategody(Category category) throws EntityDoesNotExistException {
		Category updatedCategory = getCategoryById(category.getId());
		categoryRepo.save(category);
		return category;
	}

	@Override
	public Category addCategory(Category category, Long authorId)
			throws EntityExistsException, EntityDoesNotExistException {
		
		User user=userRepo.findById(authorId).orElseThrow(()->new EntityDoesNotExistException("User with ID: "+authorId+"does not exist.")); 
				if(category.getId()!=null&&categoryRepo.findById(category.getId())!=null) 
		 			throw new EntityExistsException("Category with same ID already exists"); 
		 		user.getCategoriesModerated().add(category); 
		 		userRepo.save(user); 
				return categoryRepo.save(category); 
		 	}

	@Override
	public void save(Category theCategory) {
		categoryRepo.save(theCategory);
		
	} 


}
