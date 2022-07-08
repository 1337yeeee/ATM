import java.util.Scanner;

public class ATM {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Bank theBank = new Bank("Money Bank");

		User aUser = theBank.addUser("John", "Spice", "1234");

		Account aAccount = new Account("Checking", aUser, theBank);
		theBank.addAccount(aAccount);

		User curUser;
		while(true) {
			curUser = ATM.mainMenuPrompt(theBank, sc);

			ATM.printUserMenu(curUser, sc);
		}
	}

	private static void printUserMenu(User theUser, Scanner sc) {

		theUser.printAccountSummary();

		int choice;

		do {
			System.out.printf("\nWelcome %s! What would you like to do?\n", theUser.getFirstName());
			System.out.println(" 1. Show account transactions;");
			System.out.println(" 2. Withdrawal");
			System.out.println(" 3. Deposit");
			System.out.println(" 4. Transfer");
			System.out.println(" 5. Quit");
			System.out.println();
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			if(choice < 1 || choice > 5) {
				System.out.println("There is no such option");
			}
		} while(choice < 1 || choice > 5);

		switch(choice) {
			case 1:
				ATM.showTransactionHistory(theUser, sc);
				break;
			case 2:
				ATM.withdrawalFunds(theUser, sc);
				break;
			case 3:
				ATM.depositFunds(theUser, sc);
				break;
			case 4:
				ATM.transferFunds(theUser, sc);
				break;
		}

		if(choice != 5) {
			ATM.printUserMenu(theUser, sc);
		}

	}

	private static void depositFunds(User theUser, Scanner sc) {

		int toAcct;
		double amount;
		String memo;

		do {
			System.out.printf("Enter the number (1-%d) to choose" +
					"the account which the transaction is going to be to", theUser.numAccounts()+1);

			toAcct = sc.nextInt()-1;

			if(toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.print("Invalid account. Please try again\n");
			}
		} while(toAcct < 0 || toAcct >= theUser.numAccounts());

		// get the amount to transfer
		do {
			System.out.print("Enter the amount to transfer\n");
			amount = sc.nextDouble();

			if(amount <= 0) {
				System.out.println("Invalid amount. Please try again\n");
			}
		} while(amount <= 0);

		System.out.print("Enter a memo: ");
		memo = sc.nextLine();

		theUser.addAcctTransaction(toAcct, amount, memo);
	}

	private static void withdrawalFunds(User theUser, Scanner sc) {

		int fromAcct;
		double amount;
		double acctBalance;
		String memo;

		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) to choose" +
					"the account which the transaction is going to be from", theUser.numAccounts()+1);

			fromAcct = sc.nextInt()-1;

			if(fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.print("Invalid account. Please try again\n");
			}
		} while(fromAcct < 0 || fromAcct >= theUser.numAccounts());

		acctBalance = theUser.getAcctBalance(fromAcct);

		do {
			System.out.printf("Enter the amount to transfer\n[less than %.2f]\n", acctBalance);
			amount = sc.nextDouble();

			if(amount <= 0 || amount > acctBalance) {
				System.out.println("Invalid amount. Please try again\n");
			}
		} while(amount <= 0 || amount > acctBalance);
		sc.nextLine();

		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();

		// do the withdrawal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);

	}

	private static void transferFunds(User theUser, Scanner sc) {

		int fromAcct;
		int toAcct;
		double amount;
		double acctBalance;

		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) to choose" +
					"the account which the transaction is going to be from", theUser.numAccounts()+1);

			fromAcct = sc.nextInt()-1;

			if(fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.print("Invalid account. Please try again\n");
			}
		} while(fromAcct < 0 || fromAcct >= theUser.numAccounts());

		acctBalance = theUser.getAcctBalance(fromAcct);

		// get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) to choose" +
					"the account which the transaction is going to be to", theUser.numAccounts()+1);

			toAcct = sc.nextInt()-1;

			if(toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.print("Invalid account. Please try again\n");
			}
		} while(toAcct < 0 || toAcct >= theUser.numAccounts());

		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer\n[less than %.2f]\n", acctBalance);
			amount = sc.nextDouble();

			if(amount <= 0 || amount > acctBalance) {
				System.out.println("Invalid amount. Please try again\n");
			}
		} while(amount <= 0 || amount > acctBalance);

		theUser.addAcctTransaction(fromAcct, -1*amount,
				String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount,
				String.format("Transfer from account %s", theUser.getAcctUUID(fromAcct)));

	}

	private static void showTransactionHistory(User theUser, Scanner sc) {

		int nAcct;

		do {
			System.out.printf("Enter the number (1-%d) of the account\n"+
					"whose transactions you want to see", theUser.numAccounts());

			nAcct = sc.nextInt() - 1;

			if(nAcct < 0 || nAcct >= theUser.numAccounts()) {
				System.out.println("Invalid number of the account\nTry again");
			}
		} while(nAcct < 0 || nAcct >= theUser.numAccounts());

		theUser.printAcctTransactionHistory(nAcct);
	}

	private static User mainMenuPrompt(Bank theBank, Scanner sc) {

		String userID;
		String userPIN;
		User authUser;

		// enter the user's ID and PIN until it's correct
		do {
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter the user ID: ");
			userID = sc.nextLine();
			System.out.println("");
			System.out.print("Enter pin: ");
			userPIN = sc.nextLine();
			System.out.println("");


			authUser = theBank.userLogin(userID, userPIN);
			if(authUser == null) {
				System.out.println("Incorrect user ID and PIN combination");
			}
		} while(authUser == null);

		return authUser;
	}
}
