import java.util.Map;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;

public class ActionParser {
	String actionParser(HttpExchange he) {
		Map<String, String> parameters = new Tools().queryToMap(he.getRequestURI().getQuery());
		String action = parameters.get("action");

		if (action.matches("getJson"))
			return getJson(parameters);
		if (action.matches("getBalance"))
			return new ActionGetBalance().getBalance(parameters);
		if (action.matches("withdrawMoney"))
			return new ActionWithdrawMoney().withdrawMoney(parameters);

		return new Tools().getJsonWithError("invalid action");
	}

	private String getJson(Map<String, String> parameters) {
		return new JSONObject(parameters).toString();
	}
}