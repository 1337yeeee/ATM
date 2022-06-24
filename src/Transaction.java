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

}
