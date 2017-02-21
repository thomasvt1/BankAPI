import java.sql.SQLException;

public class App {

	private static Database db = null;
	private static boolean serverEnable = true;
	static int databaseCalled = 0;
	public static final int port = 9010;
	private static HTTPServer HTTPServer = null;

	public static void main(String[] args) {
		System.out.println("Starting...");
		db = new Database();
		db.startConnection();
		db.printStatusOfConnection();

		HTTPServer = new HTTPServer();
		HTTPServer.Start(port);

		new Commands().keepServiceOn();
	}

	protected static Database getDatabase() {
		if (db == null)
			db = new Database();
		try {
			if (db.getConn().isClosed())
				db.startConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}

	protected static void shutdownServer() {
		serverEnable = false;
		HTTPServer.Stop();
		System.exit(1);
	}

	protected static boolean getServerShutdown() {
		return serverEnable;
	}
}