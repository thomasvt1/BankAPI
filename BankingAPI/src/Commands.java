import java.util.Scanner;

public class Commands {

	static Scanner in = new Scanner(System.in);

	void keepServiceOn() {
		while(App.getServerShutdown()) {
			listener();
		}
	}

	private void listener() {
		System.out.println("Please input next command");
		String input = in.nextLine();

		if (input.equalsIgnoreCase("adduser")) {
			System.out.println("Please input pin:");
			String pin = in.nextLine();

			System.out.println("Please input cardID:");
			int cardID = Integer.parseInt(in.nextLine());

			System.out.println("Please input money to give:");
			double money = Double.parseDouble(in.nextLine());

			System.out.println("Please input first name:");
			String firstName = in.nextLine();

			System.out.println("Please input last name:");
			String lastName = in.nextLine();

			new UserManagement().createUser(pin, cardID, money, firstName, lastName);
		}
		else if (input.equalsIgnoreCase("addmoney")) {
			System.out.println("Please input user:");
			int user = Integer.parseInt(in.nextLine());

			System.out.println("Please input money:");
			int money = Integer.parseInt(in.nextLine());

			new UserManagement().addMoney(user, money);

		}
		else if (input.equalsIgnoreCase("getpinhash")) {
			System.out.println("Please input user:");
			int user = Integer.parseInt(in.nextLine());

			System.out.println(new UserManagement().getPinHash(user));
		}
		else if (input.equalsIgnoreCase("setpin")) {
			System.out.println("Please input user:");
			int user = Integer.parseInt(in.nextLine());

			System.out.println("Please input new pin:");
			String pin = in.nextLine();

			new UserManagement().setPin(user, pin);

		}
		else if (input.equalsIgnoreCase("getmoney")) {
			System.out.println("Please input user:");
			int user = Integer.parseInt(in.nextLine());

			System.out.println(new UserManagement().getBalance(user));

		}
		else if (input.equalsIgnoreCase("encryptw")) {
			System.out.println("Please enter input:");
			String s = in.nextLine();

			System.out.println("Please enter key:");
			String key = in.nextLine();

			try {
				System.out.println(new Encryption(key).encrypt(s));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (input.equalsIgnoreCase("status")) {
			System.out.println("Connection status;");
			App.getDatabase().printStatusOfConnection();

		}
		else if (input.equalsIgnoreCase("removemoney")) {
			System.out.println("Please input user:");
			int user = Integer.parseInt(in.nextLine());

			System.out.println("Please input money:");
			int money = Integer.parseInt(in.nextLine());

			new UserManagement().removeMoney(user, money);
		}
		else if (input.equalsIgnoreCase("stop")) {
			System.out.println("Stopping server...");
			App.shutdownServer();
		}
	}
}