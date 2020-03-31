package cookingrecipe.cookingrecipe.service;

import java.util.List;


import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.model.Comment;

public interface CommentService {
    Comment getCommentById(Long id) throws EntityDoesNotExistException; 
 	List<Comment> getAllComments(); 
 	Comment addComment(Comment comment, Long userId, Long recipeId) throws EntityExistsException, EntityDoesNotExistException;  
 	Comment deleteCommentById(Long id) throws EntityDoesNotExistException; 
	Comment updateComment(Comment comment) throws EntityDoesNotExistException;
    List<Comment> getCommentsByUserId(Long userId) throws EntityDoesNotExistException;
	void save(Comment theComment);  
 


}
