

public class BuildException extends Exception {
	public BuildException(String message) {
		super(message);
	}
	
	public BuildException(){
		System.out.println("\nDon't hace enough deuterium.");
	}
}
