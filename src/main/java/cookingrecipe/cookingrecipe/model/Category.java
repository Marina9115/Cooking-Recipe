package cookingrecipe.cookingrecipe.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity 
 @Table(name="categories") 
 public class Category{ 
 	@Id 
 	@GeneratedValue(strategy=GenerationType.IDENTITY) 
 	private Long id; 
 	 
 	@Column 
 	private String name; 
 	 
 	@Column 
 	private String description; 
 	 
 	@Column 
 	private String tags; 
 	 
 	@Column 
 	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
 	private LocalDateTime created; 
 	@Column
 	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
 	 private LocalDateTime modified; 
 	 	 
 	public Category() { 
 		
 	}
 	
 	

	public Category(Long id, String name, String description, String tags) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
	}



	public Category(Long id, String name, String description, String tags, LocalDateTime created,
			LocalDateTime modified) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.created = created;
		this.modified = modified;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public void setId(Long id) {
		this.id = id;
	}



	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
 	}
 	 		 
 	 	 

