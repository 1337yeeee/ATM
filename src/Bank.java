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

	/**
	 * Add an account for the bank
	 * @param account the account to add
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

}
