package banking.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {

	private double checkingB, jointB, savingsB;
	private String yourName, userN, passW, accType, accStatus;
	private int numAccHolders;
	private List<String> jointAccHolders;
	private Scanner scan = new Scanner(System.in);

	public Customer() {
		super();
		checkingB = 0.0;
		jointB = 0.0;
		savingsB = 0.0;
		yourName = null;
		userN = null;
		passW = null;
		accType = null;
		accStatus = null;
		numAccHolders = 0;
		jointAccHolders = new ArrayList<>();
	}

	public int getNumAccHolders() {
		return numAccHolders;
	}

	public void setNumAccHolders(int numAccHolders) {
		this.numAccHolders = numAccHolders;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public double getCheckingB() {
		return checkingB;
	}

	public void setCheckingB(double checkingB) {
		this.checkingB = checkingB;
	}

	public double getJointB() {
		return jointB;
	}

	public void setJointB(double jointB) {
		this.jointB = jointB;
	}

	public double getSavingsB() {
		return savingsB;
	}

	public void setSavingsB(double savingsB) {
		this.savingsB = savingsB;
	}

	public String getYourName() {
		return yourName;
	}

	public void setYourName(String yourName) {
		this.yourName = yourName;
	}

	public String getUserN() {
		return userN;
	}

	public void setUserN(String userN) {
		this.userN = userN;
	}

	public String getPassW() {
		return passW;
	}

	public void setPassW(String passW) {
		this.passW = passW;
	}

	public List<String> getJointAccHolders() {
		return jointAccHolders;
	}

	public void setJointAccHolders(List<String> jointAccHolders) {
		this.jointAccHolders = jointAccHolders;
	}

	public void register() {
		System.out.println("In Register");
	}

	public void deposit(String type) {

		double deposit = 0;

		if (type == "Checking") {
			System.out.print("Enter deposit amount: $");
			deposit = scan.nextInt();
			if (deposit > 0)
				checkingB += deposit;
			else
				System.out.println("Incorrect value was entered.");
		}

		else if (type == "Joint") {
			System.out.print("Enter deposit amount: $");
			deposit = scan.nextInt();
			if (deposit > 0)
				jointB += deposit;
			else
				System.out.println("Incorrect value was entered.");
		}

		else if (type == "Savings") {
			System.out.print("Enter deposit amount: $");
			deposit = scan.nextInt();
			if (deposit > 0)
				savingsB += deposit;
			else
				System.out.println("Incorrect value was entered.");
		}

	}

	public void withdrawal(String type) {
		double withdrawal = 0;

		if (type == "Checking") {
			System.out.print("Enter withdrawal amount: $");
			withdrawal = scan.nextInt();
			if (withdrawal > checkingB)
				System.out.println("Withdrawal amount is more than checking balance.");
			else
				checkingB -= withdrawal;
		}

		else if (type == "Joint") {
			System.out.print("Enter withdrawal amount: $");
			withdrawal = scan.nextInt();
			if (withdrawal > jointB)
				System.out.println("Withdrawal amount is more than the joint balance.");
			else
				jointB -= withdrawal;
		}

		else if (type == "Savings") {
			System.out.print("Enter withdrawal amount: $");
			withdrawal = scan.nextInt();
			if (withdrawal > savingsB)
				System.out.println("Withdrawal amount is more than savings balance.");
			else
				savingsB -= withdrawal;
		}
	}

	public void transferTo(String type) {
		double transferAmt = 0;

		if (type == "Checking") {
			if (accType == "Joint") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > jointB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						jointB -= transferAmt;
						checkingB += transferAmt;
					}
				}
			}

			else if (accType == "Savings") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > savingsB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						savingsB -= transferAmt;
						checkingB += transferAmt;
					}
				}
			}
		}

		else if (type == "Joint") {
			if (accType == "Checking") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > checkingB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						checkingB -= transferAmt;
						jointB += transferAmt;
					}
				}
			}

			else if (accType == "Savings") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > savingsB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						savingsB -= transferAmt;
						jointB += transferAmt;
					}
				}
			}
		}

		else if (type == "Savings") {
			if (accType == "Checking") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > checkingB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						checkingB -= transferAmt;
						savingsB += transferAmt;
					}
				}
			}

			else if (accType == "Joint") {
				if (canTransfer(type)) {
					System.out.println("Enter transfer amount: $");
					transferAmt = scan.nextDouble();

					if (transferAmt > jointB)
						System.out.println("The transfer amount was higher than checking balance.");
					else {
						jointB -= transferAmt;
						savingsB += transferAmt;
					}
				}
			}
		}
	}

	/*
	 * Each of the write methods stores the username last
	 * and the password right before the username
	 * See the txt files
	 *  
	 */
	public void writeChecking() {
		File outFile = new File("CheckingAccounts.txt");

		try {
			if (!outFile.exists()) {
				System.out.println("We had to make the file.");
				outFile.createNewFile();
			}

			FileWriter fw = new FileWriter(outFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println("-----");
			pw.println(accStatus);
			pw.println(accType);
			pw.println(yourName);
			pw.println(checkingB);
			pw.println(passW);
			pw.println(userN);

			pw.close();
		}

		catch (IOException e) {
			System.out.println("File not found. ");
		}
	}

	
	public void writeSavings() {
		File outFile = new File("SavingsAccounts.txt");

		try {
			if (!outFile.exists()) {
				System.out.println("File update successful. ");
				outFile.createNewFile();
			}

			FileWriter fw = new FileWriter(outFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println("-----");
			pw.println(accStatus);
			pw.println(accType);
			pw.println(yourName);
			pw.println(checkingB);
			pw.println(passW);
			pw.println(userN);

			pw.close();
		}

		catch (IOException e) {
			System.out.println("File not found. ");
		}
	}

	public void writeJoint() {
		File outFile = new File("JointAccounts.txt");

		try {
			if (!outFile.exists()) {
				System.out.println("File update successful. ");
				outFile.createNewFile();
			}

			FileWriter fw = new FileWriter(outFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println("-----");
			pw.println(accStatus);
			pw.println(accType);
			pw.println(numAccHolders);
			for (int i = 0; i < jointAccHolders.size(); i++) {
				pw.println(jointAccHolders.get(i));
			}
			pw.println(jointB);
			pw.println(passW);
			pw.println(userN);
			pw.close();
		}

		catch (IOException e) {
			System.out.println("File not found. ");
		}
	}

	public void updateChecking() {
		String userNC = null, passWC = null, dashC = null, accStatusC = null, accTypeC = null, yourNameC = null;
		double checkingBC = 0;
		boolean hasCompared = false;
		File tempFile = new File("temp.txt");
		try {

			File file = new File("CheckingAccounts.txt");

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			if (!tempFile.exists()) {
				System.out.println("We had to make the file.");
				tempFile.createNewFile();
			}

			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dashC == null)
					dashC = line;

				else if (accStatusC == null)
					accStatusC = line;

				else if (accTypeC == null)
					accTypeC = line;

				else if (yourNameC == null)
					yourNameC = line;

				else if (checkingBC == 0 && !hasCompared) {
					checkingBC = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passWC == null)
					passWC = line;

				else if (userNC == null) {
					userNC = line;

					if (userNC.equals(userN) && passWC.equals(passW)) {
						checkingBC = checkingB;
						accStatusC = accStatus;

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(yourNameC);
						pw.println(checkingBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						yourNameC = null;
						checkingBC = 0;
						hasCompared = false;
					}

					else {

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(yourNameC);
						pw.println(checkingBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						yourNameC = null;
						checkingBC = 0;
						hasCompared = false;
					}
				}

			}

			br.close();
			pw.close();
			
			
			// Delete the original file
			if (!file.delete()) {
				System.out.println("Could not delete file. ");
			}
	
			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(file))
				System.out.println("Could not rename file. ");
			
		}

		catch (IOException e) {
			System.out.println("File not found. ");
		}
	}

	public void updateSavings() {
		String userNC = null, passWC = null, dashC = null, accStatusC = null, accTypeC = null, yourNameC = null;
		double savingsBC = 0;
		boolean hasCompared = false;
		File tempFile = new File("temp.txt");
		try {

			File file = new File("SavingsAccounts.txt");

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			if (!tempFile.exists()) {
				System.out.println("We had to make the file.");
				tempFile.createNewFile();
			}

			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dashC == null)
					dashC = line;

				else if (accStatusC == null)
					accStatusC = line;

				else if (accTypeC == null)
					accTypeC = line;

				else if (yourNameC == null)
					yourNameC = line;

				else if (savingsBC == 0 && !hasCompared) {
					savingsBC = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passWC == null)
					passWC = line;

				else if (userNC == null) {
					userNC = line;

					if (userNC.equals(userN) && passWC.equals(passW)) {
						savingsBC = savingsB;
						accStatusC = accStatus;

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(yourNameC);
						pw.println(savingsBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						yourNameC = null;
						savingsBC = 0;
						hasCompared = false;
					}

					else {

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(yourNameC);
						pw.println(savingsBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						yourNameC = null;
						savingsBC = 0;
						hasCompared = false;
					}
				}

			}

			br.close();
			pw.close();

			// Delete the original file
			if (!file.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(file))
				System.out.println("Could not rename file");
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void updateJoint() {
		String userNC = null, passWC = null, dashC = null, accStatusC = null, accTypeC = null;
		List<String> jointAccHoldersC = new ArrayList<>();
		int numAccHoldersC = 0, count = 0;
		double jointBC = 0;
		boolean hasCompared = false;
		File tempFile = new File("temp.txt");
		try {

			File file = new File("JointAccounts.txt");

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			if (!tempFile.exists()) {
				System.out.println("We had to make the file.");
				tempFile.createNewFile();
			}

			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dashC == null)
					dashC = line;

				else if (accStatusC == null)
					accStatusC = line;

				else if (accTypeC == null)
					accTypeC = line;

				else if (numAccHoldersC == 0)
					numAccHoldersC = Integer.parseInt(line.trim());

				else if (count < numAccHoldersC) {
						jointAccHoldersC.add(line);
						count++;
				}

				else if (jointBC == 0 && !hasCompared) {
					jointBC = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passWC == null)
					passWC = line;

				else if (userNC == null) {
					userNC = line;

					if (userNC.equals(userN) && passWC.equals(passW)) {
						jointBC = jointB;
						accStatusC = accStatus;

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(numAccHoldersC);
						for (int i = 0; i < jointAccHoldersC.size(); i++) {
							pw.println(jointAccHoldersC.get(i));
						}
						pw.println(jointBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						numAccHoldersC = 0;
						jointAccHoldersC.clear();
						jointBC = 0;
						hasCompared = false;
						count = 0;
					}

					else {

						pw.println(dashC);
						pw.println(accStatusC);
						pw.println(accTypeC);
						pw.println(numAccHoldersC);
						for (int i = 0; i < jointAccHoldersC.size(); i++) {
							pw.println(jointAccHoldersC.get(i));
						}
						pw.println(jointBC);
						pw.println(passWC);
						pw.println(userNC);

						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						numAccHoldersC = 0;
						jointAccHoldersC.clear();
						jointBC = 0;
						hasCompared = false;
						count = 0;
					}
				}

			}

			br.close();
			pw.close();
			
			
			// Not working along with the others like it
			// Delete the original file
			if (!file.delete()) {
				System.out.println("Could not delete file. ");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(file))
				System.out.println("Could not rename file. ");
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void accessChecking() {
		String userN2, passW2;
		boolean hasCompared = false;
		String dash = null;
		int select = 0;

		System.out.print("Enter username: ");
		userN2 = scan.next();

		System.out.print("Enter password: ");
		passW2 = scan.next();

		try {
			File file = new File("CheckingAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null) && ((!userN2.equals(userN)) || (!passW2.equals(passW)))) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (yourName == null)
					yourName = line;

				else if ((checkingB == 0) && !hasCompared)
				{
					checkingB = Double.parseDouble(line.trim()); 
					hasCompared = true;	
				}
				
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;

					if (userN2.equals(userN) && passW2.equals(passW)) {

						if (accStatus.equals("open")) {
							System.out.println("Your account has not yet been approved.");
						}

						else if (accStatus.equals("denied")) {
							System.out.println("Your account has been denied.");
						}

						else if (accStatus.equals("approved")) {

							System.out.println("\nName: " + yourName);
							System.out.println("Account Balance: $" + checkingB);
							System.out.println("Username: " + userN);
							System.out.println("Password: " + passW);
							System.out.println("Account: " + accType);
							System.out.println("Account Status: " + accStatus);
							System.out.println("\nSelect an option:");
							System.out.println("1 - Deposit");
							System.out.println("2 - Withdrawal");
							System.out.println("3 - Transfer");
							System.out.println("4 - Exit");
							select = scan.nextInt();

							switch (select) {
							case 1:
								deposit(accType);
								updateChecking();
								break;
							case 2:
								withdrawal(accType);
								updateChecking();
								break;
							case 3:
								System.out.println("");
								System.out.println("1 - Checking to Joint");
								System.out.println("2 - Checking to Saving ");
								System.out.println("3 - Cancel");
								select = scan.nextInt();

								switch (select) {
								case 1:
									transferTo("Joint");
									updateChecking();
									updateJoint();
									break;

								case 2:
									transferTo("Savings");
									updateChecking();
									break;

								default:
									System.out.println("Transaction canceled.");
									break;
								}

								break;

							default:
								break;
							}
						}
					}

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						yourName = null;
						checkingB = 0;
						hasCompared = false;
					}
				}	
			}
			
			br.close();

			if ((!userN2.equals(userN)) || (!passW2.equals(passW)))
				System.out.println("\nYour profile could not be found or doesn't exist.");
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void accessSavings() {
		String userN2, passW2;
		boolean hasCompared = false;

		String dash = null;
		int select = 0;

		System.out.print("Enter username: ");
		userN2 = scan.next();

		System.out.print("Enter password: ");
		passW2 = scan.next();

		try {
			File file = new File("SavingsAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null) && ((!userN2.equals(userN)) || (!passW2.equals(passW)))) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (yourName == null)
					yourName = line;

				else if ((savingsB == 0) && !hasCompared) {
					savingsB = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;

					if (userN2.equals(userN) && passW2.equals(passW)) {

						if (accStatus.equals("open")) {
							System.out.println("Your account has not yet been approved.");
						}

						else if (accStatus.equals("denied")) {
							System.out.println("Your account has been denied.");
						}

						else if (accStatus.equals("approved")) {

							System.out.println("\nName: " + yourName);
							System.out.println("Account Balance: $" + savingsB);
							System.out.println("Username: " + userN);
							System.out.println("Password: " + passW);
							System.out.println("Account: " + accType);
							System.out.println("Account Status: " + accStatus);
							System.out.println("\nSelect an option:");
							System.out.println("1 - Deposit");
							System.out.println("2 - Withdrawal");
							System.out.println("3 - Transfer");
							System.out.println("4 - Exit");
							select = scan.nextInt();

							switch (select) {
							case 1:
								deposit(accType);
								updateSavings();
								break;
							case 2:
								withdrawal(accType);
								updateSavings();
								break;
							case 3:
								System.out.println("");
								System.out.println("1 - Savings to Checking");
								System.out.println("2 - Savings to Joint");
								System.out.println("3 - Cancel");
								select = scan.nextInt();

								switch (select) {
								case 1:
									transferTo("Checking");
									updateSavings();
									break;

								case 2:
									transferTo("Joint");
									updateSavings();
									break;

								default:
									System.out.println("Transaction canceled.");
									break;
								}

								break;

							default:
								break;
							}
						}
					}

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						yourName = null;
						savingsB = 0;
						hasCompared = false;
					}
				}

			}
			
			br.close();

			if ((!userN2.equals(userN)) || (!passW2.equals(passW)))
				System.out.println("\nYour profile could not be found or doesn't exist.");
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void accessJointAcc() {
		String userN2, passW2;
		boolean hasCompared = false;
		String dash = null;
		int select = 0, count = 0;

		System.out.print("Enter username: ");
		userN2 = scan.next();

		System.out.print("Enter password: ");
		passW2 = scan.next();

		try {
			File file = new File("JointAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null) && ((!userN2.equals(userN)) || (!passW2.equals(passW)))) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (numAccHolders == 0)
					numAccHolders = Integer.parseInt(line.trim());

				else if (count < numAccHolders) {
						jointAccHolders.add(line);
						count++;
				}

				else if ((jointB == 0) && !hasCompared) {
					jointB = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;

					if (userN2.equals(userN) && passW2.equals(passW)) {

						if (accStatus.equals("open")) {
							System.out.println("Your account has not yet been approved.");
						}

						else if (accStatus.equals("denied")) {
							System.out.println("Your account has been denied.");
						}

						else if (accStatus.equals("approved")) {

							System.out.println("\nAccount Holders: " + jointAccHolders);
							System.out.println("Account Balance: $" + jointB);
							System.out.println("Username: " + userN);
							System.out.println("Password: " + passW);
							System.out.println("Account: " + accType);
							System.out.println("Account Status: " + accStatus);
							System.out.println("Select an option:");
							System.out.println("1 - Deposit");
							System.out.println("2 - Withdrawal");
							System.out.println("3 - Transfer");
							System.out.println("4 - Exit");
							select = scan.nextInt();

							switch (select) {
							case 1:
								deposit(accType);
								updateJoint();
								break;
							case 2:
								withdrawal(accType);
								updateJoint();
								break;
							case 3:
								System.out.println("");
								System.out.println("1 - Joint to Checking");
								System.out.println("2 - Joint to Saving ");
								System.out.println("3 - Cancel");
								select = scan.nextInt();

								switch (select) {
								case 1:
									transferTo("Checking");
									updateJoint();
									break;

								case 2:
									transferTo("Savings");
									updateJoint();
									break;

								default:
									System.out.println("Transaction canceled.");
									break;
								}

								break;

							default:
								break;
							}
						}
					}

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						numAccHolders = 0;
						jointAccHolders.clear();
						jointB = 0;
						hasCompared = false;
						count = 0;
					}
				}

			}
			
			br.close();
			if ((!userN2.equals(userN)) || (!passW2.equals(passW)))
				System.out.println("\nYour profile could not be found or doesn't exist.");
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public void viewSavings() {
		boolean hasCompared = false;
		String dash = null;
		int select = 0;

		try {
			File file = new File("SavingsAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (yourName == null)
					yourName = line;

				else if ((savingsB == 0) && !hasCompared)
				{
					savingsB = Double.parseDouble(line.trim()); 
					hasCompared = true;	
				}
				
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;
					
					System.out.println("\nName: " + yourName);
					System.out.println("Account Balance: $" + savingsB);
					System.out.println("Username: " + userN);
					System.out.println("Password: " + passW);
					System.out.println("Account: " + accType);
					System.out.println("Account Status: " + accStatus);

						if (accStatus.equals("open")) {
							System.out.println("\nThis account needs approving.");
							System.out.println("1 - Approve");
							System.out.println("2 - Deny");
							System.out.println("3 - Do Nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "approved";
								updateSavings();
								break;
							case 2:
								accStatus = "denied";
								updateSavings();
								break;

							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							savingsB = 0;
							hasCompared = false;
						}

						else if (accStatus.equals("denied")) {
							System.out.println("This has been denied.");
							System.out.println("1 - To Approve");
							System.out.println("2 - Do nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "approved";
								updateSavings();
								break;
						
							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							savingsB = 0;
							hasCompared = false;
						}

						else if (accStatus.equals("approved")) {
							System.out.println("This account has been approved");
							System.out.println("1 - To Deny");
							System.out.println("2 - Do nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "denied";
								updateSavings();
								break;

							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							savingsB = 0;
							hasCompared = false;
						}
					

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						yourName = null;
						savingsB = 0;
						hasCompared = false;
					}
				}

			}
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public void viewChecking() {
		boolean hasCompared = false;
		String dash = null;
		int select = 0;

		try {
			File file = new File("CheckingAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (yourName == null)
					yourName = line;

				else if ((checkingB == 0) && !hasCompared)
				{
					checkingB = Double.parseDouble(line.trim()); 
					hasCompared = true;	
				}
				
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;
					
					System.out.println("\nName: " + yourName);
					System.out.println("Account Balance: $" + checkingB);
					System.out.println("Username: " + userN);
					System.out.println("Password: " + passW);
					System.out.println("Account: " + accType);
					System.out.println("Account Status: " + accStatus);

						if (accStatus.equals("open")) {
							System.out.println("\nThis account needs approving.");
							System.out.println("1 - To Approve");
							System.out.println("2 - To Deny");
							System.out.println("3 - Do Nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "approved";
								updateChecking();
								break;
							case 2:
								accStatus = "denied";
								updateChecking();
								break;

							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							checkingB = 0;
							hasCompared = false;
						}

						else if (accStatus.equals("denied")) {
							System.out.println("This has been denied.");
							System.out.println("1 - To Approve");
							System.out.println("2 - Do nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "approved";
								updateChecking();
								break;
						
							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							checkingB = 0;
							hasCompared = false;
						}

						else if (accStatus.equals("approved")) {
							System.out.println("This account has been approved");
							System.out.println("1 - To Deny");
							System.out.println("2 - Do nothing");
							select = scan.nextInt();
							
							switch (select) {
							case 1:
								accStatus = "denied";
								updateChecking();
								break;

							default:
								System.out.println("Continuing on");
								break;
							}
							
							dash = null;
							userN = null;
							passW = null;
							accType = null;
							accStatus = null;
							yourName = null;
							checkingB = 0;
							hasCompared = false;
						}
					

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						yourName = null;
						checkingB = 0;
						hasCompared = false;
					}
				}

			}
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public void viewJoint() {
		boolean hasCompared = false;
		String dash = null;
		int select = 0, count = 0;

		try {
			File file = new File("JointAccounts.txt");

			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null)) {
				if (dash == null)
					dash = line;

				else if (accStatus == null)
					accStatus = line;

				else if (accType == null)
					accType = line;

				else if (numAccHolders == 0)
					numAccHolders = Integer.parseInt(line.trim());

				else if (count < numAccHolders) {
						jointAccHolders.add(line);
						count++;
				}

				else if ((jointB == 0) && !hasCompared) {
					jointB = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (passW == null)
					passW = line;

				else if (userN == null) {
					userN = line;
					
					System.out.println("\nAccount Holders: " + jointAccHolders);
					System.out.println("Account Balance: $" + jointB);
					System.out.println("Username: " + userN);
					System.out.println("Password: " + passW);
					System.out.println("Account: " + accType);
					System.out.println("Account Status: " + accStatus);

					if (accStatus.equals("open")) {
						System.out.println("\nThis account needs approving.");
						System.out.println("1 - To Approve");
						System.out.println("2 - To Deny");
						System.out.println("3 - Do Nothing");
						select = scan.nextInt();
						
						switch (select) {
						case 1:
							accStatus = "approved";
							updateJoint();
							break;
						case 2:
							accStatus = "denied";
							updateJoint();
							break;

						default:
							System.out.println("Continuing on");
							break;
						}
						
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						numAccHolders = 0;
						jointAccHolders.clear();
						jointB = 0;
						hasCompared = false;
						count = 0;
					}

					else if (accStatus.equals("denied")) {
						System.out.println("This has been denied.");
						System.out.println("1 - To Approve");
						System.out.println("2 - Do nothing");
						select = scan.nextInt();
						
						switch (select) {
						case 1:
							accStatus = "approved";
							updateJoint();
							break;
					
						default:
							System.out.println("Continuing on");
							break;
						}
						
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						numAccHolders = 0;
						jointAccHolders.clear();
						jointB = 0;
						hasCompared = false;
						count = 0;
					}

					else if (accStatus.equals("approved")) {
						System.out.println("This account has been approved");
						System.out.println("1 - To Deny");
						System.out.println("2 - Do nothing");
						select = scan.nextInt();
						
						switch (select) {
						case 1:
							accStatus = "denied";
							updateJoint();
							break;

						default:
							System.out.println("Continuing on");
							break;
						}
						
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						numAccHolders = 0;
						jointAccHolders.clear();
						jointB = 0;
						hasCompared = false;
						count = 0;
					}

					else {
						dash = null;
						userN = null;
						passW = null;
						accType = null;
						accStatus = null;
						numAccHolders = 0;
						jointAccHolders.clear();
						jointB = 0;
						hasCompared = false;
						count = 0;
					}
				}
			}
		}

		catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public boolean canTransfer(String type) {
		String userN2, passW2, userNC = null, passWC = null, dashC = null, accStatusC = null, accTypeC = null,
				yourNameC = null;
		List<String> jointAccHoldersC = new ArrayList<>();
		int numAccHoldersC = 0;
		boolean hasCompared = false;

		System.out.print("Enter username: ");
		userN2 = scan.next();

		System.out.print("Enter password: ");
		passW2 = scan.next();

		File file = null;
		FileReader fr = null;

		try {

			if (type == "Checking") {
				file = new File("CheckingAccounts.txt");
			}

			else if (type == "Joint") {
				file = new File("JointAccounts.txt");
			}

			else if (type == "Savings") {
				file = new File("SavingsAccounts.txt");
			}

			fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (((line = br.readLine()) != null) && ((!userN2.equals(userNC)) || (!passW2.equals(passWC)))) {
				if (dashC == null)
					dashC = line;

				else if (accStatusC == null)
					accStatusC = line;

				else if (accTypeC == null)
					accTypeC = line;

				else if ((type == "Checking" || type == "Savings") && yourNameC == null) {
					yourNameC = line;
				}

				else if (type == "Joint" && numAccHoldersC == 0) {
					numAccHoldersC = Integer.parseInt(line.trim());
				}

				else if (type == "Joint" && jointAccHoldersC.isEmpty()) {
					for (int i = 0; i < numAccHoldersC; i++)
						jointAccHoldersC.add(line);
				}

				else if (type == "Checking" && checkingB == 0 && !hasCompared) {
					checkingB = Double.parseDouble(line.trim());
					hasCompared = true;
				}
				else if (type == "Joint" && jointB == 0 && !hasCompared) {
					jointB = Double.parseDouble(line.trim());
					hasCompared = true;
				}

				else if (type == "Savings" && savingsB == 0 && !hasCompared) {
					savingsB = Double.parseDouble(line.trim());
					hasCompared = true;
				}

				else if (passWC == null)
					passWC = line;

				else if (userNC == null) {
					userNC = line;

					if (userN2.equals(userNC) && passW2.equals(passWC)) {

						if (accStatusC.equals("open")) {
							System.out.println("Your account has not yet been approved.");
							
							if (type == "Checking")
								checkingB = 0;
							else if (type == "Savings")
								savingsB = 0;
							else
								jointB = 0;
							return false;
						}

						else if (accStatusC.equals("denied")) {
							System.out.println("Your account has been denied.");
							if (type == "Checking")
								checkingB = 0;
							else if (type == "Savings")
								savingsB = 0;
							else
								jointB = 0;
							return false;
						}

						else if (accStatusC.equals("approved")) {
							if (type.equals("Checking")) {
								updateChecking();
								checkingB = 0;
							}
							else if (type.equals("Savings")) {
								updateSavings();
								savingsB = 0;
							}
							else {
								updateJoint();
								jointB = 0;
							}
							return true;
						}
					}

					else {
						dashC = null;
						userNC = null;
						passWC = null;
						accTypeC = null;
						accStatusC = null;
						jointAccHoldersC.clear();
						hasCompared = false;
						if (type == "Checking")
							checkingB = 0;
						else if (type == "Savings")
							savingsB = 0;
						else
							jointB = 0;
					}
				}

			}

			if ((!userN2.equals(userNC)) || (!passW2.equals(passWC)))
				System.out.println("\nYour profile could not be found or doesn't exist.");
		}

		catch (IOException e) {
			System.out.println("File not found. ");
		}
		return false;
	}

}
