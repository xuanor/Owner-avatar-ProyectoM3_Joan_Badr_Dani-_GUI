
public class ResourceException extends Exception{
	
	public ResourceException(String message) {
		super(message);
	}
	
	public ResourceException(){
		System.out.println("You don't have enough resources");
	}
}
