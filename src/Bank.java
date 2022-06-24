import java.util.ArrayList;

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


	public String getNewUserUUID() {
	}

	public String getNewAccountUUID() {
	}

	/**
	 * Add an account for the bank
	 * @param account the account to add
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

}
