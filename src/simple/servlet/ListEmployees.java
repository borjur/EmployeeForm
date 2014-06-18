package simple.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import simple.bean.Employee;

/**
 * Servlet implementation class ListEmployees
 */
public class ListEmployees extends HttpServlet {
	
	@Resource(name="jdbc/Employee")
	private DataSource ds;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.sql.Connection connection = null;
		java.sql.ResultSet resultSet = null;
		java.sql.Statement statement = null;

		try {
			//Get database connection from data source
			connection = ds.getConnection();
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery("SELECT * from EMPLOYEE");

		    ArrayList<Employee> employees = new ArrayList<Employee> ();
		    while (resultSet.next()) {
		        Employee eachEmployee = new Employee();
		        eachEmployee.setFirstName(resultSet.getString("FIRSTNAME")); 
		        eachEmployee.setLastName(resultSet.getString("LASTNAME"));
		        eachEmployee.setMiddleName(resultSet.getString("MIDINIT"));
		        eachEmployee.setEmployeeNo(resultSet.getString("EMPNO"));
		        //Add the employee to the list
		        employees.add(eachEmployee);
		    }
		    //Store the list in the request context.
		    request.setAttribute("employees", employees);
		    //Invoke the JSP
		    request.getRequestDispatcher("list_employees.jsp").forward(
		        request, response);
		} catch (Throwable ex) {
			ex.printStackTrace();
		    throw new ServletException(ex);
		} finally {
		    try {
		        if (resultSet != null)
		            resultSet.close();
		        if (statement != null)
		            statement.close();
		        if (connection != null)
		            connection.close();
		    } catch (Exception ex) {
		        throw new ServletException("Error while cleaning up", ex);
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
