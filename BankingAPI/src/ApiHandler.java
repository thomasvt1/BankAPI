import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.SystemUtils;

public class ApiHandler implements HttpHandler {

	boolean debug = true; //Will disable key auth

	public void handle(HttpExchange he) throws IOException {
		System.out.println("Connection from: " + getIp(he));

		if (checkParameters(he)) {
			returnPage(he, "");
			return;
		}

		if (!keyCorrect(he)) {
			returnPage(he, "BAD KEY");
			return;
		}

		if (actionProvided(he)) {
			returnPage(he, new ActionParser().actionParser(he));
		}

		returnPage(he, new Tools().getJsonWithError("We Dont Know What Happened Bank"));

	}

	/*
	 * Returns the IP of the connection. Even when behind (Apache) proxy.
	 */
	private String getIp(HttpExchange he) {
		if (SystemUtils.IS_OS_LINUX)
			return he.getRequestHeaders().getFirst("X-forwarded-for");
		else
			return he.getRemoteAddress().getAddress()+"";
	}

	/*
	 * Checks if HttpExchange contains the parameter 'action'
	 */
	private boolean actionProvided(HttpExchange he) {
		Map<String, String> parameters;
		parameters = new Tools().queryToMap(he.getRequestURI().getQuery());

		return parameters.get("action") != null;
	}

	/*
	 * Method called when want to send generated data to client.
	 */
	private void returnPage(HttpExchange he, String response) throws IOException {
		he.sendResponseHeaders(200, response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.toString().getBytes());
		os.close();
	}

	/*
	 * Checks if parameters were given.
	 */
	boolean checkParameters(HttpExchange he) {
		return he.getRequestURI().getQuery() == null;
	}

	/*
	 * Checks if the key (time based) matches to ensure safe communication.
	 */
	boolean keyCorrect(HttpExchange he) {
		if (debug)
			return true;

		Map<String, String> parameters;
		parameters = new Tools().queryToMap(he.getRequestURI().getQuery());

		if (parameters.get("key") == null)
			return false;

		return new Tools().matchRequestKey(parameters.get("key"));
	}
}