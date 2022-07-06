import java.util.ArrayList;

public class Account {

	/**
	 * account name
	 */
	private String name;
	/**
	 * The ID for the account
	 */
	private String uuid;
	/**
	 * the user - holder of the account
	 */
	private User holder;
	/**
	 * the list of the transactions of this account
	 */
	private ArrayList<Transaction> transactions;

	/**
	 * create new account
	 * @param name      the name of this account
	 * @param holder    the user which has this account
	 * @param theBank   the bank that issues this account
	 */
	public Account(String name, User holder, Bank theBank) {

		this.name = name;
		this.holder = holder;

		// get new account UUID
		this.uuid = theBank.getNewAccountUUID();

		// create empty array list for transactions
		this.transactions = new ArrayList<Transaction>();

		// add account to the holder's and the bank's lists of accounts
		this.holder.addAccount(this);
		theBank.addAccount(this);
	}

	public String getUUID() {
		return uuid;
	}
}
