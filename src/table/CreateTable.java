package table;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateTable {

	public static final String DB_URL = "jdbc:sqlite:/Users/dongik/Desktop/dongik/Project/DongKi/DongKi/db/my.db";

	public static void main(String[] args) {
		// create table sql
		var sql = "CREATE TABLE IF NOT EXISTS warehouses ("
			+ " date text NOT NULL,"
			+ " question text,"
			+ " answer text,"
			+ "is_done INTEGER"
			+ ");";

		try (var conn = DriverManager.getConnection(DB_URL);
			 var stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
