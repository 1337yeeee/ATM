import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

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
	 * The MD5 hash of the user's pin
	 */
	private byte pinHash[];
	/**
	 * The list of user's accounts
	 */
	private ArrayList<Account> accounts;

	/**
	 * Create a new user
	 *
	 * @param firstName the user's first name
	 * @param lastName  the user's last name
	 * @param pin       the user's account pin
	 * @param theBank   the Bank object that the user is a customer of
	 */
	public User(String firstName, String lastName, String pin, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;

		//store the pin's MD5 hash, rather than it's original value
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		//get a new Unique Universal ID for the user
		this.uuid = theBank.getNewUserUUID();

		//create empty list of accounts
		this.accounts = new ArrayList<Account>();

		//debug print
		System.out.printf("New user %s %s created\nUser's uuid: %s\n", this.lastName, this.firstName, this.uuid);
	}

	/**
	 * Add an account for the user
	 *
	 * @param account the account to add
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public String getUUID() {
		return uuid;
	}

	public boolean validatePIN(String pin) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	public String getFirstName() {
		return this.firstName;
	}

	public void printAccountSummary() {

		System.out.printf("\n\n%s's accounts summary\n", this.firstName);

		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.printf("%d) %s", i + 1, this.accounts.get(i).getSummaryLine());
		}

		System.out.println();
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAcctTransactionHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransactionHistory();
	}

	public double getAcctBalance(int fromAcct) {
		return this.accounts.get(fromAcct).getBalance();
	}

	public String  getAcctUUID(int toAcct) {
		return this.accounts.get(toAcct).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}
