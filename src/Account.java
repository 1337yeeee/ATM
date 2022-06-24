import java.util.ArrayList;

public class Account {

	/**
	 * account name
	 */
	private String name;
	/**
	 * balance on the account
	 */
	private double balance;
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

}
