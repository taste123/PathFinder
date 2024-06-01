package pathfinder.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	private static final String URL = "jdbc:sqlite:pathFinder.db";

	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
			System.out.println("Koneksi berhasil ke SQLite.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
