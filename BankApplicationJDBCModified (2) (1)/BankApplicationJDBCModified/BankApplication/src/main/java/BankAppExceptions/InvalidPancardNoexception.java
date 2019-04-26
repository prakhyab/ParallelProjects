package BankAppExceptions;

public class InvalidPancardNoexception extends Exception{
	public InvalidPancardNoexception() {
           System.err.println("Pancard No is not valid");
	}

}
