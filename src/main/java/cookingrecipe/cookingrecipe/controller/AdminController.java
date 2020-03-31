package cookingrecipe.cookingrecipe.controller;

 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Controller; 
 import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.RequestMapping; 

 
import cookingrecipe.cookingrecipe.service.UserService; 
 
 
@Controller 
 @RequestMapping("/admin") 
 public class AdminController { 
 	private UserService userService; 
 	@Autowired 
 	public AdminController(UserService userService) { 
 		this.userService=userService; 
 	} 
 	
 	
 	 
 	@GetMapping("/menu") 
 	public String adminMenu(Model model) { 
 		model.addAttribute("allUsers", userService.getAllUsers()); 
 		model.addAttribute("view", "/admin/admin-menu"); 
 		return "layout"; 
 	} 
 } 
