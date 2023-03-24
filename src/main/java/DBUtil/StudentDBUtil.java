package DBUtil;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.Student;

//thao tác với database
//helper class
public class StudentDBUtil {
	private DataSource dataSource;

	public StudentDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		// kết nối
		Connection connection = null;
		// Tuyên bố
		Statement statement = null;
		// tập kết quả
		ResultSet result = null;
		try {
			// create a connector
			connection = dataSource.getConnection();
			// create a statement from connection upon
			statement = connection.createStatement();
			// sql
			String sql = "select * from student";

			// excute query save to result
			result = statement.executeQuery(sql);

			// process result set
			while (result.next()) {
				int id = result.getInt("id");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");

				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);

			}

		} finally {
			close(connection, statement, result);
		}
		return students;

	}

	public void delete(String studentID) throws Exception {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			
			statement = connection.createStatement();
			String sql = "delete from student where id =" + studentID;
			
			statement.execute(sql);

		} finally {
			close(connection, statement, null);
		}

	}
	
	
	public Student getStudent(String studentID) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			
			statement = connection.createStatement();
			String sql = "select * from student where id = " + studentID;
			/// lấy kết quả thì excuteQuery
			result= statement.executeQuery(sql);
			
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				
				return new Student(Integer.parseInt(studentID), firstName,lastName, email);
				
			}
			return null;
			

		} finally {
			close(connection, statement, result);
		}

	}

	public void addStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();

			String sql = "insert into student values (?,?,?)";

			statement = connection.prepareStatement(sql);

			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getEmail());

			statement.execute();

		} finally {
			close(connection, statement, null);
		}

	}

	public void updateStudent(Student student) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();

			String sql = "update student" 
			          +" set first_name = ?, last_name=?,email= ? " + "where id = ?";

			statement = connection.prepareStatement(sql);

			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getEmail());
			statement.setInt(4, student.getId());

			statement.execute();

		} finally {
			close(connection, statement, null);
		}

	}

	public void close(Connection connect, Statement statement, ResultSet result) {
		try {
			if (connect != null)
				connect.close();
			if (statement != null)
				statement.close();
			if (result != null)
				result.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
