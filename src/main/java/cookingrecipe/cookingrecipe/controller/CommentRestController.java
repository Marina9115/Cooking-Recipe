package cookingrecipe.cookingrecipe.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityExistsException;
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
import cookingrecipe.cookingrecipe.exception.InvalidRequestException;
import cookingrecipe.cookingrecipe.model.Comment;
import cookingrecipe.cookingrecipe.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
 @RestController 
 @RequestMapping("/api/comment/") 
 @CrossOrigin(origins = "http://localhost:3000/api/comment/", maxAge = 3600) 
 public class CommentRestController { 
 	private CommentService commentService; 
 	@Autowired 
 	public CommentRestController(CommentService commentService) { 
 		this.commentService=commentService; 
 	} 
 	 
 	@GetMapping 
 	public List<Comment> getAllComment() { 
 		return commentService.getAllComments(); 
 	} 
 
 
 	@GetMapping("{id}") 
 	public Comment getCommentById(@PathVariable Long id) throws EntityDoesNotExistException { 
 		return commentService.getCommentById(id); 
 	} 
 	@PostMapping("{authorId}/{recipeId}") 
 		public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable Long authorId, @PathVariable Long recipeId) throws  EntityDoesNotExistException, cookingrecipe.cookingrecipe.exception.EntityExistsException { 
 	 		Comment commentAdded = commentService.addComment(comment, authorId, recipeId); 
 	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}") 
 	 				.buildAndExpand(commentAdded.getId()).toUri(); 
 	 		return ResponseEntity.created(location).body(commentAdded); 
 	 	} 
 	
 	@DeleteMapping("{id}") 
 	 	public ResponseEntity<Comment> deleteCommentById(@PathVariable Long id) throws EntityDoesNotExistException { 
 	 		Comment commentToBeDeleted = getCommentById(id); 
 	 		commentService.deleteCommentById(id); 
 	 		return ResponseEntity.accepted().body(commentToBeDeleted); 
 	 	} 
 	 
 	 
 	 	@PutMapping("{id}") 
 	 	public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) 
 	 			throws InvalidRequestException, EntityDoesNotExistException { 
 	 		if (!id.equals(comment.getId())) 
 	 			throw new InvalidRequestException("ID(" + id + ") and comment ID(" + comment.getId() + ") don't match."); 
 	 		return ResponseEntity.accepted().body(commentService.updateComment(comment)); 
 	 	} 
 	 } 


 	

