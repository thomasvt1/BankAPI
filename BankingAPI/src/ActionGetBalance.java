import java.util.HashMap;
import java.util.Map;

public class ActionGetBalance {

	String getBalance(Map<String, String> parameters) {
		Map<String, String> response = new HashMap<String, String>();

		String auth = new Tools().authUserOrReturnError(parameters);
		if (!auth.matches("OK"))
			return auth;

		int user = Integer.parseInt(parameters.get("user"));

		response.put("balance", new UserManagement().getBalance(user)+"");
		return response.toString();
	}
}