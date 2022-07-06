import java.util.Date;

public class Transaction {

	/**
	 * the amount of this transaction
	 */
	private double amount;
	/**
	 * the time and nate of this transaction
	 */
	private Date timestamp;
	/**
	 * a memo of this transaction
	 */
	private String memo;
	/**
	 * the account in wich the transaction was performed
	 */
	private Account inAccount;

	/**
	 * Create a mew Transaction
	 * @param amount        the amount transacted
	 * @param inAccount     the account the transaction belongs to
	 */
	public Transaction(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}

	/**
	 * Create a mew Transaction
	 * @param amount        the amount transacted
	 * @param memo          the memo for the transaction
	 * @param inAccount     the account the transaction belongs to
	 */
	public Transaction(double amount, String memo, Account inAccount) {

		// call the 2-args constructor
		this(amount, inAccount);

		// set the memo
		this.memo = memo;

	}

}
