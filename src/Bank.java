import java.util.ArrayList;
import java.util.Random;

public class Bank {

	/**
	 * the name of this bank
	 */
	private String name;
	/**
	 * the list of users of this bank
	 */
	private ArrayList<User> users;
	/**
	 * the list of accounts of this bank
	 */
	private ArrayList<Account> accounts;


	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * generate new universally unique ID
	 * @return the uuid
	 */
	public String getNewUserUUID() {
		String uuid;
		Random rand = new Random();
		int len = 6;
		boolean nonUnique;

		// continue looping till we generate an unique ID
		do {
			// generate the uuid
			uuid = "";
			for(int i=0; i<len; i++) {
				uuid += ((Integer)rand.nextInt(10)).toString();
			}

			// check if the uuid is unique
			nonUnique = false;
			for (User u: this.users) {
				if(uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);


		return uuid;
	}

	public String getNewAccountUUID() {
		String uuid;
		Random rand = new Random();
		int len = 6;
		boolean nonUnique;

		// continue looping till we generate an unique ID
		do {
			// generate the uuid
			uuid = "";
			for(int i=0; i<len; i++) {
				uuid += ((Integer)rand.nextInt(10)).toString();
			}

			// check if the uuid is unique
			nonUnique = false;
			for (Account a: this.accounts) {
				if(uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);


		return uuid;
	}

	public User addUser(String firstName, String lastName, String pin) {

		// create a new user object and add it to ArrayList<User> users
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);

		// create a savings account for the user
		Account newAccount = new Account("Savings", newUser, this);

		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);

		return newUser;
	}

	public User userLogin(String userID, String pin) {

		for(User u: this.users) {
			if(u.getUUID().compareTo(userID) == 0 && u.validatePIN(pin)) {
				return u;
			}
		}

		return null;

	}

	public void addAccount(Account theAccount) {
		this.accounts.add(theAccount);
	}

	public String getName() {
		return this.name;
	}
}
