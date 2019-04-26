package BankAppExceptions;

public class InvalidAadharNoException extends Exception {
	
	public InvalidAadharNoException() {
		 
		System.err.println("  Aadhar No is not valid");
	}

}
