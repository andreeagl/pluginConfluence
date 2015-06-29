package de.dialogdata.confluence.plugins.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.dialogdata.confluence.plugins.entity.Employee;

public class EmployeeService {

	private static EmployeeService INSTANCE;

	private final static String SELECT_EMPLOYEES = "SELECT *FROM EMPLOYEES";
	private final static String SAVE_EMPLOYEE = "INSERT INTO EMPLOYEES (E_NAME, TEAM, START_DATE, END_DATE) "
			+ "	values (?, ?, ?, ?)";
	private final static String DELETE_EMPLOYEE = "DELETE FROM EMPLOYEES WHERE E_NAME = ?";

	public static EmployeeService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EmployeeService();
		}
		return INSTANCE;
	}

	private EmployeeService() {
	}

	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEES);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Employee employee = new Employee();
				employee.setName(result.getString("E_NAME"));
				employee.setTeam(result.getString("TEAM"));
				employee.setStartDate(result.getDate("START_DATE"));
				employee.setEndDate(result.getDate("END_DATE"));
				employees.add(employee);
			}
			return employees;
		} catch (Exception e) {
			throw e;
		}

	}

	public void saveEmployee(Employee employee) throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(SAVE_EMPLOYEE);
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getTeam());
			if (employee.getStartDate() != null) {
				statement.setDate(3, new java.sql.Date(employee.getStartDate().getTime()));
			} else {
				statement.setDate(3, null);
			}
			if (employee.getEndDate() != null) {
				statement.setDate(4, new java.sql.Date(employee.getEndDate().getTime()));
			} else {
				statement.setDate(4, null);
			}

			statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public void deleteEmployee(String name) throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	private Connection getConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/confdb";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
