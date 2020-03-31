package cookingrecipe.cookingrecipe.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "recipes")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String title;

	@Column
	@NotNull
	private String shortDescription;

	@Column
	@NotNull
	private Integer cookingTime;

	@Column
	@NotNull
	private String usedProducts;
	@Column
	@NotNull
	private String picture;

	@Column
	@NotNull
	private String description;

	@Column
	@NotNull
	private String tags;

	@Column
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	private LocalDateTime created;

	@Column
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	private LocalDateTime modified;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Comment> coments;

	public Recipe() {

	}
	

	public Recipe(Long id, @NotNull String title, @NotNull String shortDescription, @NotNull Integer cookingTime,
			@NotNull String usedProducts, @NotNull String picture, @NotNull String description, @NotNull String tags,
			Category category, User author) {
		super();
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.cookingTime = cookingTime;
		this.usedProducts = usedProducts;
		this.picture = picture;
		this.description = description;
		this.tags = tags;
		this.category = category;
		this.author = author;
	}


	public Recipe(Long id, @NotNull String title, @NotNull String shortDescription, @NotNull Integer cookingTime,
			@NotNull String usedProducts, @NotNull String picture, @NotNull String description, @NotNull String tags,
			LocalDateTime created, LocalDateTime modified, Category category, User author, Set<Comment> coments) {
		super();
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.cookingTime = cookingTime;
		this.usedProducts = usedProducts;
		this.picture = picture;
		this.description = description;
		this.tags = tags;
		this.created = created;
		this.modified = modified;
		this.category = category;
		this.author = author;
		this.coments = coments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Integer getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(Integer cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getUsedProducts() {
		return usedProducts;
	}

	public void setUsedProducts(String usedProducts) {
		this.usedProducts = usedProducts;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Set<Comment> getComents() {
		return coments;
	}

	public void setComents(Set<Comment> coments) {
		this.coments = coments;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
