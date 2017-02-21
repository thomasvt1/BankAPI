import java.util.HashMap;
import java.util.Map;

public class ActionWithdrawMoney {

	public String withdrawMoney(Map<String, String> parameters) {
		Map<String, String> response = new HashMap<String, String>();

		String auth = new Tools().authUserOrReturnError(parameters);
		if (!auth.matches("OK"))
			return auth;

		if (!new Tools().isDouble(parameters.get("amount")))
			return new Tools().getJsonWithError("amount not double");

		int user = Integer.parseInt(parameters.get("user"));
		double balance = new UserManagement().getBalance(user);
		double amountToWithdraw = Double.parseDouble(parameters.get("amount"));

		if (balance < amountToWithdraw)
			return new Tools().getJsonWithError("not enough balance");
		if (amountToWithdraw < 0)
			return new Tools().getJsonWithError("can not withdraw negative amount");
		if (amountToWithdraw < 1)
			return new Tools().getJsonWithError("can not withdraw too small amount");

		new UserManagement().removeMoney(user, amountToWithdraw);

		response.put("balance", (balance - amountToWithdraw) + "");
		response.put("response", "success");

		return response.toString();
	}
}