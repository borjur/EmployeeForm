<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EmployeeBeanResponse</title>
</head>
<body>
<jsp:useBean id="employee" class="simple.bean.Employee">
	<jsp:setProperty name="employee" property="*" />
</jsp:useBean>

<%final java.util.List missingFields = new java.util.ArrayList();
if (employee.getLastName() == null || employee.getLastName().length() == 0) {
    missingFields.add("Last Name");
}
if (employee.getEmployeeNo() == null || 
    employee.getEmployeeNo().length() == 0) {
    missingFields.add("Employee Number");
}
if (missingFields.isEmpty() == false) {
    throw new simple.bean.MissingInputException("Fields were missing: " 
        + missingFields.toString());
}%>

<P>
First name: <jsp:getProperty name="employee" property="firstName"/><BR>
Last name: <jsp:getProperty name="employee" property="lastName"/><BR>
Middle name: <jsp:getProperty name="employee" property="middleName"/><BR>
Employee No.: <jsp:getProperty name="employee" property="employeeNo"/><BR>
</P>
</body>
</html>