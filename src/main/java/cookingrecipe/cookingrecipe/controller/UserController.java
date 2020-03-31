package cookingrecipe.cookingrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
 import org.springframework.ui.Model; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.PathVariable; 
 import org.springframework.web.bind.annotation.RequestMapping;

import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.service.UserService;



@Controller 
 @RequestMapping("/user") 
 public class UserController { 
     private UserService userService; 
 
 
     @Autowired 
     public UserController(UserService userService){ 
         this.userService=userService; 
     } 


         @GetMapping("/{username}") 
          public String getUser(Model model, @PathVariable String username){ 
              try { 
                 model.addAttribute("user", userService.getUsersByUsername(username)); 
                  model.addAttribute("view", "/user/details"); 
              } catch (Exception e) { 
                 model.addAttribute("view", "/error/404"); 
              } 
     
              
             return "layout"; 
          } 
      
         }
