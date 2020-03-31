package cookingrecipe.cookingrecipe.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	@Size(min = 2, max = 15, message = "User's first name should be between 2 and 15 characters long")
	private String firstName;

	@Column
	@NotNull
	@Size(min = 2, max = 15, message = "User's last name should be between 2 and 15 characters long")
	private String lastName;

	@Column(unique = true)
	@NotNull
	@Email
	private String email;

	@Column(unique = true)
	@NotNull
	@Size(min = 2, max = 15, message = "User's username should be between 2 and 15 characters long")
	private String username;

	@Column
	@NotNull
//	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&\\.\\,])[A-Za-z\\d@$!%*#?&\\.\\,]{8,15}$", message="") 
	private String password;

	@Column
	@Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)")
	private String picture;

	@Column
	@Pattern(regexp = "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\\\"]{20,2500}")
	private String description;

	@Column
	private String metadata;

	@Column
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	LocalDateTime created;
	
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm:ss")
	@Column
	private LocalDateTime modified;

	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;

	// @NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="roles")
	private Set<Role> roles;
	@Column
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Recipe> recipes;

	@OneToMany
	@JoinColumn(name = "categories_moderated")
	@JsonProperty(access = Access.WRITE_ONLY)
	Set<Category> categoriesModerated;

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Recipe> favouriteRecipes;

	@OneToMany
	@JoinColumn(name = "favourite_cooks")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<User> favouriteCooks;

	public User() {
	}

	public User(Long id,
			@NotNull @Size(min = 2, max = 15, message = "User's first name should be between 2 and 15 characters long") String firstName,
			@NotNull @Size(min = 2, max = 15, message = "User's last name should be between 2 and 15 characters long") String lastName,
			@NotNull @Email String email,
			@NotNull @Size(min = 2, max = 15, message = "User's username should be between 2 and 15 characters long") String username,
			@NotNull String password,
			@Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)") String picture,
			@Pattern(regexp = "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\\\"]{20,2500}") String description,
			String metadata, Gender gender, Set<Role> roles, Status status) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.picture = picture;
		this.description = description;
		this.metadata = metadata;
		this.gender = gender;
		this.roles = roles;
		this.status = status;
	}

	public User(Long id,
			@NotNull @Size(min = 2, max = 15, message = "User's first name should be between 2 and 15 characters long") String firstName,
			@NotNull @Size(min = 2, max = 15, message = "User's last name should be between 2 and 15 characters long") String lastName,
			@NotNull @Email String email,
			@NotNull @Size(min = 2, max = 15, message = "User's username should be between 2 and 15 characters long") String username,
			@NotNull String password,
			@Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)") String picture,
			@Pattern(regexp = "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\\\"]{20,2500}") String description,
			String metadata, LocalDateTime created, LocalDateTime modified, Gender gender, Set<Role> roles,
			Status status) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.picture = picture;
		this.description = description;
		this.metadata = metadata;
		this.created = created;
		this.modified = modified;
		this.gender = gender;
		this.roles = roles;
		this.status = status;
	}

	public User(Long id,
			@NotNull @Size(min = 2, max = 15, message = "User's first name should be between 2 and 15 characters long") String firstName,
			@NotNull @Size(min = 2, max = 15, message = "User's last name should be between 2 and 15 characters long") String lastName,
			@NotNull @Email String email,
			@NotNull @Size(min = 2, max = 15, message = "User's username should be between 2 and 15 characters long") String username,
			@NotNull String password,
			@Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)") String picture,
			@Pattern(regexp = "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\\\"]{20,2500}") String description,
			String metadata, LocalDateTime created, LocalDateTime modified, Gender gender, Set<Role> roles,
			Status status, Set<Comment> comments, Set<Recipe> recipes, Set<Category> categoriesModerated,
			Set<Recipe> favouriteRecipes, Set<User> favouriteCooks) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.picture = picture;
		this.description = description;
		this.metadata = metadata;
		this.created = created;
		this.modified = modified;
		this.gender = gender;
		this.roles = roles;
		this.status = status;
		this.comments = comments;
		this.recipes = recipes;
		this.categoriesModerated = categoriesModerated;
		this.favouriteRecipes = favouriteRecipes;
		this.favouriteCooks = favouriteCooks;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Set<Category> getCategoriesModerated() {
		return categoriesModerated;
	}

	public void setCategoriesModerated(Set<Category> categoriesModerated) {
		this.categoriesModerated = categoriesModerated;
	}

	public Set<Recipe> getFavouriteRecipes() {
		return favouriteRecipes;
	}

	public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
		this.favouriteRecipes = favouriteRecipes;
	}

	public Set<User> getFavouriteCooks() {
		return favouriteCooks;
	}

	public void setFavouriteCooks(Set<User> favouriteCooks) {
		this.favouriteCooks = favouriteCooks;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
}
