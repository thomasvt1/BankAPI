import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class Tools {

	/*
	 * Returns 'OK' when user is authenticated. Else will return (Json) string why not.
	 */
	String authUserOrReturnError(Map<String, String> parameters) {
		if (parameters.get("user") == null)
			return getJsonWithError("no user provided");
		if (parameters.get("pin") == null)
			return getJsonWithError("no pin provided");
		if (!isInteger(parameters.get("user")))
			return getJsonWithError("user not int");

		int user = Integer.parseInt(parameters.get("user"));
		String pin = parameters.get("pin");

		if (!new UserManagement().accountExists(user))
			return new Tools().getJsonWithError("no user");

		if (!new Tools().pinCorrect(user, pin))
			return new Tools().getJsonWithError("pin incorrect");
		return "OK";
	}

	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public String getCurrentKey() {
		LocalDateTime date = LocalDateTime.now();
		int prefix = date.getMinute() * 9 + date.getDayOfMonth() + 5 + date.getDayOfYear() * 3 * date.getMonthValue() * 9 * date.getYear();

		String suffix = "" + date.getMinute() + (date.getYear() * date.getDayOfMonth()) + (date.getMinute() * date.getMinute()) + date.getDayOfYear() + (date.getDayOfYear() * date.getDayOfMonth());

		return StringUtils.substring(prefix + suffix, 0, 16);
	}

	public String getJsonWithError(String error) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("error", error);
		return new JSONObject(response).toString();
	}

	public Map<String, String> queryToMap(String query){
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length>1) {
				result.put(pair[0], pair[1]);
			}else{
				result.put(pair[0], "");
			}
		}
		return result;
	}

	/*
	 * Returns true if pin provided is correct
	 * user: userid (String) int
	 * encryptedPin (String) encrypted/decrypted pincode (4 long)
	 */
	boolean pinCorrect(String user, String pin) {
		String hashedUserPin = new UserManagement().getPinHash(user);
		String givenPinHash = new Hashing().hashString(pin);


		return hashedUserPin.matches(givenPinHash);
	}

	boolean pinCorrect(int user, String encryptedPin) {
		return pinCorrect(user+"", encryptedPin);
	}


	boolean matchRequestKey(String providedKey) {
		String keyToCheck = "LOL";
		String postedKey = StringUtils.substring(providedKey, 0, 14);
		Encryption enc = new Encryption(getCurrentKey());

		try {
			String givenKey = postedKey;
			String genKey = enc.encrypt(keyToCheck);
			System.out.println("Current key: " + genKey);
			if (genKey.contains(givenKey) && givenKey.length() < genKey.length())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}