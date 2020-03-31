package cookingrecipe.cookingrecipe.exception;

public class EntityDoesNotExistException extends Exception {

	public EntityDoesNotExistException() { 
	 	} 
		
		 
	 	public EntityDoesNotExistException(String message) { 
	 		super(message); 
		 	} 
		 
		 
		 	public EntityDoesNotExistException(Throwable cause) { 
		 		super(cause); 
		 	} 
		 
		 
		 	public EntityDoesNotExistException(String message, Throwable cause) { 
				super(message, cause); 
		 	} 
		 
		 
		 	public EntityDoesNotExistException(String message, Throwable cause, boolean enableSuppression, 
		 			boolean writableStackTrace) { 
		 		super(message, cause, enableSuppression, writableStackTrace); 
	 	} 
		
		 
		 } 
