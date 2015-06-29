package de.dialogdata.confluence.plugins.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import de.dialogdata.confluence.plugins.entity.Employee;
import de.dialogdata.confluence.plugins.services.EmployeeService;

@Path("/")
public class EmployeeResource {

	@POST
	@AnonymousAllowed
	@Consumes((MediaType.APPLICATION_JSON))
	@Path("/save-employee")
	public Response addEmployee(Employee employee){
		EmployeeService employeeService = EmployeeService.getInstance();
		try {
			employeeService.saveEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	@POST
	@AnonymousAllowed
	@Consumes((MediaType.APPLICATION_JSON))
	@Path("/remove-employee")
	public Response deleteEmployee(String name){
		EmployeeService employeeService = EmployeeService.getInstance();
		try {
			employeeService.deleteEmployee(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	@GET
	@AnonymousAllowed
	@Produces((MediaType.APPLICATION_JSON))
	@Path("/get-all-employees")
	public Response getAllEmployees(){
		EmployeeService employeeService = EmployeeService.getInstance();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = employeeService.getAllEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(employees).build();
	}
}
