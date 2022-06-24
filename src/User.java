import java.util.ArrayList;

public class User {

	/**
	 * The first name of the user
	 */
	private String firstName;
	/**
	 * The last name of the user
	 */
	private String lastName;
	/**
	 * The ID of the user
	 */
	private String uuid;
	/**
	 * The MD5 hash of the user's pin number
	 */
	private byte pinHash[];
	/**
	 * The list of user's accounts
	 */
	private ArrayList<Account> accounts;

}
