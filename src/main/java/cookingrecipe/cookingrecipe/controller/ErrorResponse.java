package cookingrecipe.cookingrecipe.controller;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NonNull; 
import lombok.RequiredArgsConstructor;  
 
@Data 
 @AllArgsConstructor 
 @RequiredArgsConstructor 
 public class ErrorResponse { 
 	@NonNull 
 	private String response; 
 	private int code; 
 } 
