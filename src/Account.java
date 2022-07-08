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

	}

	public String getUUID() {
		return uuid;
	}

	public String getSummaryLine() {

		//get the account's balance
		double balance = this.getBalance();

		if(balance >= 0) {
			return String.format("%s : $%.2f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.2f) : %s", this.uuid, balance, this.name);
		}
	}

	public double getBalance() {

		double balance = 0;

		for(Transaction t: this.transactions) {
			balance += t.getAmount();
		}

		return balance;
	}

	public void printTransactionHistory() {

		System.out.printf("\n\n===Transaction history for account %s===\n", this.uuid);

		for(int i=this.transactions.size()-1; i>=0; i--) {
			System.out.printf(this.transactions.get(i).getSummaryLine());
		}

		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		Transaction trans = new Transaction(amount, memo, this);
		this.transactions.add(trans);
	}
}
