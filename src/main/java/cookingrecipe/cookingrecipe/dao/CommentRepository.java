package cookingrecipe.cookingrecipe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cookingrecipe.cookingrecipe.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	
}
