package cookingrecipe.cookingrecipe.controller;

import javax.validation.Valid; 
 
  import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.AnonymousAuthenticationToken; 
import org.springframework.security.core.Authentication; 
 import org.springframework.security.core.context.SecurityContextHolder; 
 import org.springframework.stereotype.Controller; 
 import org.springframework.ui.Model; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RequestParam; 



import cookingrecipe.cookingrecipe.model.UserBindingModel;
import cookingrecipe.cookingrecipe.service.RecipeService;
import cookingrecipe.cookingrecipe.service.UserService;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.model.User;
import cookingrecipe.cookingrecipe.model.Role;

@Controller 
@RequestMapping("/") 
 public class HomeController { 
 
 
    private UserService us; 

 
     @Autowired 
     public HomeController(UserService us){ 
         this.us=us; 
     } 
    
     @GetMapping("login") 
        public String login(Model model, @RequestParam(required = false) String error){ 
          	if(error!=null) { 
          		model.addAttribute("error", "Invalid credentials"); 
          	} 
              model.addAttribute("view", "login"); 
              return "layout"; 
          } 

    
     
          @GetMapping 
          public String home(Model model){ 
          	Authentication au=SecurityContextHolder.getContext().getAuthentication(); 
          	if(!(au instanceof AnonymousAuthenticationToken)) { 
      	        User user=(User) au.getPrincipal(); 
      	        model.addAttribute("name", "Name: "+user.getFirstName()+" "+user.getLastName()); 
     	        model.addAttribute("userName","Username: "+ user.getUsername()); 
      	        model.addAttribute("email", "Email: "+user.getEmail()); 
      	        model.addAttribute("role", "Role: "+user.getRoles().stream().findFirst().orElseGet(()->new Role("")).getAuthority()); 
      	        model.addAttribute("view", "home"); 
      	        model.addAttribute("isLogedIn", "smth"); 
              } 
          	else { 
      	        model.addAttribute("status", "Status: Not Logged"); 
      	        model.addAttribute("view", "home"); 
          	} 
          	model.addAttribute("users", us.getAllUsers()); 
              return "layout"; 
          } 
      
      
          @GetMapping("register") 
          public String register(Model model,@Valid @RequestParam(required = false) String error){ 
          	Authentication au=SecurityContextHolder.getContext().getAuthentication(); 
          	if(!(au instanceof AnonymousAuthenticationToken)) { 
          		return home(model); 
          	} 
          	else { 
          		model.addAttribute("view", "register"); 
          		return "layout"; 
          	} 
              
          } 
      
      
          @PostMapping("register") 
          public String register(Model model, @Valid UserBindingModel bm) throws EntityExistsException { 
              User user=new User(); 
              user.setPassword(bm.getPassword()); 
              user.setUsername(bm.getUsername()); 
              user.setEmail(bm.getEmail()); 
              user.setFirstName(bm.getFirstName()); 
              user.setLastName(bm.getLastName()); 
              us.addHomeCook(user); 
              return "redirect:/login"; 
          } 
      
       
      
      
      } 
