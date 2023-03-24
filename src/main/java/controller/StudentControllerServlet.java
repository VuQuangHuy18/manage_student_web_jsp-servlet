package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DBUtil.StudentDBUtil;
import entity.Student;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDBUtil studentDbUtil;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	public StudentControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			studentDbUtil = new StudentDBUtil(dataSource);

			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}

			switch (theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request, response);
				break;

			default:
				listStudents(request, response);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String studentId = request.getParameter("studentId");
		studentDbUtil.delete(studentId);
		
		listStudents(request, response);
		
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student  student = new Student(id, firstName, lastName, email);
		
		studentDbUtil.updateStudent(student);
		//send them back to the " list student" page
		
		listStudents(request, response);
		
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id  = request.getParameter("studentId");
		
		
		Student student = studentDbUtil.getStudent(id);
		System.out.println(student.toString());
		
		request.setAttribute("THE_STUDENT", student);
		
		//send to jsp page: update-student-from.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/update-student-form.jsp");
		dispatcher.forward(request, response);
	
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		studentDbUtil.addStudent(new Student(firstName, lastName, email));
		listStudents(request, response);
		
		
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		//add student to the request
		request.setAttribute("STUDENT_LIST", students);
		
		//send to JSP page (view)
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp\\list-student.jsp");
		dispatcher.forward(request, response);

	}

}
