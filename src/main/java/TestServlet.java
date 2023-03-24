import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		
		out.println("<html><body>");
		out.println("<h2>Hello World </h2>");
		out.println("<hr>");
		out.println("Time on the server is: "+ new java.util.Date());
		out.println("</body></html>");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from Student";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			int i = 1;
			while (myRs.next()) {

				String id = myRs.getString("id");
				String firstName = myRs.getString("first_Name");
				String lastname = myRs.getString("last_Name");
				String email = myRs.getString("email");
				out.println("<html><body>");
				out.println("<h1>NGUOI THU : " + i + "</h1>");
				out.println("</body></html>");
				out.println(id);
				out.println(firstName);
				out.println(lastname);
				out.println(email);
				out.println();
				i++;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
