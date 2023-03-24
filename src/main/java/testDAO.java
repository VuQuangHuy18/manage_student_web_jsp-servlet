

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class testDAO
 */
@WebServlet("/testDAO")
public class testDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	private StudentDBUtil studentDbUtil;
	
	
//	@Override
//		public void init() throws ServletException {
//			super.init();
//			try {
//				 studentDbUtil = new StudentDBUtil(dataSource);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
	
	
  
	
 
    public testDAO() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			studentDbUtil =new StudentDBUtil(dataSource);
//			List<Student> students = studentDbUtil.getStudents();
//			request.setAttribute("Student_List", students);
//			
//			// send to JPS page
//			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/viewStudent.jsp");
//			dispatcher.forward(request, response);
			
			//test getStudent
			
			studentDbUtil.updateStudent(new Student(2, "Jihmmy", "CostTa", "Jimmy@mi.com"));
			
			
			
			//test add
			//studentDbUtil.addStudent(new Student("HUY", "VU", "huy182001@gmail.com"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
