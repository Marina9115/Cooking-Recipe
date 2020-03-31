package cookingrecipe.cookingrecipe.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cookingrecipe.cookingrecipe.dao.CommentRepository;
import cookingrecipe.cookingrecipe.dao.RecipeRepository;
import cookingrecipe.cookingrecipe.dao.UserRepository;
import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.model.Comment;
import cookingrecipe.cookingrecipe.model.Recipe;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	private CommentRepository commentRepo;
	private UserRepository userRepo;
	private RecipeRepository recipeRepo;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepo, UserRepository userRepo, RecipeRepository recipeRepo) {
		this.commentRepo = commentRepo;
		this.userRepo = userRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Comment getCommentById(Long id) throws EntityDoesNotExistException {
		return commentRepo.findById(id)
				.orElseThrow(() -> new EntityDoesNotExistException("Comment with id: " + id + " does not exist."));

	}

	@Override
	public List<Comment> getAllComments() {

		return commentRepo.findAll();
	}

	

	@Override
	public Comment deleteCommentById(Long id) throws EntityDoesNotExistException {
		Comment commentToBeDeleted = getCommentById(id);
		commentRepo.deleteById(id);
		return commentToBeDeleted;
	}

	@Override
	public Comment updateComment(Comment comment) throws EntityDoesNotExistException {
		getCommentById(comment.getId());
		return commentRepo.save(comment);
	}

	public List<Comment> getCommentsByUserId(Long userId) throws EntityDoesNotExistException {
		cookingrecipe.cookingrecipe.model.User user = userRepo.findById(userId)
				.orElseThrow(() -> new EntityDoesNotExistException("User with ID:" + userId + " does not exist."));
		return new ArrayList<Comment>(user.getComments());
	}

	@Override
	public Comment addComment(Comment comment, Long userId, Long recipeId)
			throws EntityExistsException, EntityDoesNotExistException {
		User user=userRepo.findById(userId).orElseThrow(()->new EntityDoesNotExistException("User with ID:"+userId+" does not exist.")); 
		 		Recipe recipe=recipeRepo.findById(userId).orElseThrow(()->new EntityDoesNotExistException("User with ID:"+userId+" does not exist.")); 
				user.getComments().add(comment); 
		 		recipe.getComents().add(comment); 
		 		userRepo.save(user); 
		 		recipeRepo.save(recipe); 
		 		return commentRepo.save(comment); 
		 	}

	@Override
	public void save(Comment theComment) {
		// TODO Auto-generated method stub
		commentRepo.save(theComment);
	} 
		  

	}


	
