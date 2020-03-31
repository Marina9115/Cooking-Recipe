package cookingrecipe.cookingrecipe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column
	private String url;

	@Column
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	private LocalDateTime created;

	@Column
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	private LocalDateTime modified;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;

	public Comment() {
		this.created = LocalDateTime.now();
		this.modified = LocalDateTime.now();
	}

	public Comment(Long id, String text, String url, User author, Recipe recipe) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.created = LocalDateTime.now();
		this.modified = LocalDateTime.now();
		this.author = author;
		this.recipe = recipe;
	}

	

	public Comment(Long id, String text, String url, LocalDateTime created, LocalDateTime modified, User author,
			Recipe recipe) {

		this.id = id;
		this.text = text;
		this.url = url;
		this.created = created;
		this.modified = modified;
		this.author = author;
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;

	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}