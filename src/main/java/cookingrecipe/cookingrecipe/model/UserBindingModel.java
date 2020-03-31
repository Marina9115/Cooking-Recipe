package cookingrecipe.cookingrecipe.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class UserBindingModel {
	 
	@Size(min=2, max=15, message="First name should be between 2 and 15 characters") 
    private String firstName;
	
	@Size(min=2, max=15, message="Last name should be between 2 and 15 characters") 
	private String lastName;
	
	@Email(message="Invalid Email.") 
	private String email;
	
	
	@Size(min=2, max=15, message="Username should be between 2 and 15 characters long.") 
    private String username;
	
//	 	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&\\.\\,])[A-Za-z\\d@$!%*#?&\\.\\,]{8,15}$", 
//				message="Password must be between 8 and 15 characters long and contain at least on letter, number and symbol") 

	private String password;

	public UserBindingModel() {
	}

	public UserBindingModel(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
