package code;
/**
 * Token class. Each token contains a number 1-20 and 25 and a boolean value which decides
 * if the token is face down or not.
 * @author team112
 *
 */

public class Token {
	
	private int _tokenNumber;
	private boolean faceDown;
	
	/**
	 * Constructor for token
	 * @param number number on the token
	 */
	public Token(int number){
		_tokenNumber = number;
	}
	
	/**
	 * Getter method for token number
	 * @return returns the token number
	 */
	public int getTokenNumber(){
		return _tokenNumber;
	}
	
	/**
	 * Setter method for token number
	 * @param number number on the token
	 */
	public void setTokenNumber(int number){
		_tokenNumber = number;
	}
	
	/**
	 * Getter method for boolean value stating if the token is faced down
	 * @param number the number on the token which is being checked
	 * @return returns a boolean value stating if the token is faced down
	 */
	public boolean isFacedDown(int number){
		return faceDown;
	}
	
	/**
	 * Setter method for setting the token to face down
	 */
	public void setFaceDown(){
		faceDown = true;
	}
}
