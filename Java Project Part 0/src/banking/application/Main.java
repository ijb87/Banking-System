package banking.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<String> jointAccHolders = new ArrayList<>();
		Customer cust = new Customer();
		Scanner scan = new Scanner(System.in);
		int select = 0;

		System.out.println("1 - Customer");
		System.out.println("2 - Employee");
		System.out.println("3 - Bank Administrator");
		System.out.print("Enter an option: ");
		select = scan.nextInt();

		switch (select) {
		case 1:
			System.out.println("1 - Register");
			System.out.println("2 - Login/Access");
			System.out.print("Enter an option: ");
			select = scan.nextInt();

			switch (select) {
			case 1:
				System.out.println("1 - Checking");
				System.out.println("2 - Joint");
				System.out.println("3 - Saving");
				System.out.println("Choose the account type: ");
				select = scan.nextInt();
				scan.nextLine();

				switch (select) {
				case 1:
					System.out.print("Enter your name: ");
					cust.setYourName(scan.nextLine());

					System.out.print("\nUsername must be at least 8 characters ");
					System.out.print("and must begin with a alphabet.");
					System.out.print("\nEnter username: ");
					cust.setUserN(scan.nextLine());

					while (cust.getUserN().length() < 8 || !Character.isAlphabetic(cust.getUserN().charAt(0))) {
						System.out.print("\nEnter username: ");
						cust.setUserN(scan.nextLine());
					}

					System.out.print("\nPassword must be at least 7 characters ");
					System.out.print("and it can't begin with a number.");
					System.out.print("\nEnter password: ");
					cust.setPassW(scan.next());
                                        System.out.println("\nYou have created your account. Please restart the application. ");

					while (cust.getPassW().length() < 7 || Character.isDigit(cust.getPassW().charAt(0))) {
						System.out.print("\nPlease enter a valid password: ");
						cust.setPassW(scan.next());
					}

					cust.setAccType("Checking");
					cust.setAccStatus("open");
					cust.writeChecking();

					break;

				case 2:
					System.out.print("Enter the number of account holders: ");
					cust.setNumAccHolders(scan.nextInt());
					scan.nextLine();

					System.out.print("Enter the names for this account: ");
					for (int i = 0; i < cust.getNumAccHolders(); i++) {
						jointAccHolders.add(scan.next());
					}

					cust.setJointAccHolders(jointAccHolders);

					System.out.print("\nUsername must be at least 8 characters ");
					System.out.print("and must begin with a alphabet.");
					System.out.print("\nEnter username: ");
					cust.setUserN(scan.next());

					while (cust.getUserN().length() < 8 || !Character.isAlphabetic(cust.getUserN().charAt(0))) {
						System.out.print("\nPlease enter a valid username: ");
						cust.setUserN(scan.nextLine());
					}

					System.out.print("\nPassword must be at least 7 characters ");
					System.out.print("and it can't begin with a number.");
					System.out.print("\nEnter password: ");
					cust.setPassW(scan.next());
                                        System.out.println("\nYou have created your account. Please restart the application. ");


					while (cust.getPassW().length() < 7 || Character.isDigit(cust.getPassW().charAt(0))) {
						System.out.print("\nPlease enter a valid password: ");
						cust.setPassW(scan.next());
					}

					cust.setAccType("Joint");
					cust.setAccStatus("open");
					cust.writeJoint();

					break;

				case 3:
					System.out.print("Enter your name: ");
					cust.setYourName(scan.nextLine());

					System.out.print("\nUsername must be at least 8 characters ");
					System.out.print("and must begin with a alphabet.");
					System.out.print("\nEnter username: ");
					cust.setUserN(scan.next());

					while (cust.getUserN().length() < 8 || !Character.isAlphabetic(cust.getUserN().charAt(0))) {
						System.out.print("\nPlease enter a valid username: ");
						cust.setUserN(scan.nextLine());
					}

					System.out.print("\nPassword must be at least 7 characters ");
					System.out.print("and it can't begin with a number.");
					System.out.print("\nEnter password: ");
					cust.setPassW(scan.next());
                                        System.out.println("\nYou have created your account. Please restart the application. ");


					while (cust.getPassW().length() < 7 || Character.isDigit(cust.getPassW().charAt(0))) {
						System.out.print("\nPlease enter a valid password: ");
						cust.setPassW(scan.next());
					}

					cust.setAccType("Savings");
					cust.setAccStatus("open");
					cust.writeSavings();
					break;

				default:
					System.out.println("The selection did match.");
					break;

				}
				break;

			case 2:
				System.out.println("Select the account ");
				System.out.println("1 - Checking");
				System.out.println("2 - Joint");
				System.out.println("3 - Savings");
				select = scan.nextInt();

				switch (select) {
				case 1:
					cust.accessChecking();
					break;

				case 2:
					cust.accessJointAcc();
					break;

				case 3:
					cust.accessSavings();
					break;

				default:
					break;
				}

				break;

			default:
				break;
			}

			break;

		case 2:
			System.out.println("Select the acount types for viewing/approving: ");
			System.out.println("1 - Checking");
			System.out.println("2 - Joint");
			System.out.println("3 - Savings");
			System.out.println("4 - Finish");
			select = scan.nextInt();
			
			switch (select) {
			case 1:
				cust.viewChecking();
				break;
			
			case 2:
				cust.viewJoint();
				break;
			
			case 3:
				cust.viewSavings();
				break;

			default:
				System.out.println("Finish");
				break;
			}
			break;
			
		case 3:
			System.out.println("Select the acount types for viewing/approving/transaction editing: ");
			System.out.println("1 - Checking");
			System.out.println("2 - Joint");
			System.out.println("3 - Savings");
			System.out.println("4 - Finish");
			select = scan.nextInt();
			
			switch (select) {
			case 1:
				cust.accessChecking();
				cust.viewChecking();
				break;
			
			case 2:
				cust.accessJointAcc();
				cust.viewJoint();
				break;
			
			case 3:
				cust.accessSavings();
				cust.viewSavings();
				break;

			default:
				System.out.println("Finish");
				break;
			}
			break;
			
		default:
			break;
		}
		scan.close();

	}
}