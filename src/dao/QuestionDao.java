package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import table.CreateTable;

public class QuestionDao {

	public void saveQuestionAndAnswer(String question, String answer) {

		String sql = "INSERT INTO warehouses(date, question, answer, progress, is_done) VALUES(?,?,?,?,?)";

		try (var conn = DriverManager.getConnection(CreateTable.DB_URL);
			 var pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, LocalDate.now().toString());
				pstmt.setString(2, question);
				pstmt.setString(3, answer);
				pstmt.setString(4, "FIRST");
				pstmt.setInt(5, 0);

				pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public int getTodayTotalQuestionCnt() {

		String d1 = LocalDateTime.now().minusDays(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d2 = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d3 = LocalDateTime.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d4 = LocalDateTime.now().minusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d5 = LocalDateTime.now().minusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();

		String[] checkDates = new String[]{d1, d2, d3, d4, d5};

		var sql = "SELECT COUNT(*) AS count FROM warehouses WHERE date = ?";

		int cnt = 0;

		try (var conn = DriverManager.getConnection(CreateTable.DB_URL);
			 var stmt = conn.prepareStatement(sql)) {

			for (String checkDate : checkDates) {
				stmt.setString(1, checkDate);
				var rs = stmt.executeQuery();
				cnt += rs.getInt("count");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return cnt;
	}

	public String[][] getTodayTotalQuestionDetail() {

		String d1 = LocalDateTime.now().minusDays(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d2 = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d3 = LocalDateTime.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d4 = LocalDateTime.now().minusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String d5 = LocalDateTime.now().minusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();

		String[] checkDates = new String[]{d1, d2, d3, d4, d5};

		List<String[]> qnaList = new ArrayList<>();

		var sql = "SELECT ROWID as id, question, answer FROM warehouses WHERE date = ?";

		try (var conn = DriverManager.getConnection(CreateTable.DB_URL);
			 var stmt = conn.prepareStatement(sql)) {

			for (String checkDate : checkDates) {
				stmt.setString(1, checkDate);
				var rs = stmt.executeQuery();

				while(rs.next()) {
					String[] qna = new String[2];
					qna[0] = rs.getString("question");
					qna[1] = rs.getString("answer");

					qnaList.add(qna);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return qnaList.toArray(new String[0][0]);
	}

}
