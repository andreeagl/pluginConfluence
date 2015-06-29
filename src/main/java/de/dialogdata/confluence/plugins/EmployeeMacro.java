package de.dialogdata.confluence.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.util.velocity.VelocityUtils;

import de.dialogdata.confluence.plugins.entity.Employee;
import de.dialogdata.confluence.plugins.services.EmployeeService;


public class EmployeeMacro implements Macro{

	private final PageManager pageManager;
	private final SpaceManager spaceManager;
	
	public EmployeeMacro(PageManager pageManager, SpaceManager spaceManager) {
		this.pageManager = pageManager;
		this.spaceManager = spaceManager;
	}

	@Override
	public String execute(Map<String, String> arg0, String arg1, ConversionContext arg2)
			throws MacroExecutionException {
		List<Employee> employees = new ArrayList<Employee>();
		EmployeeService service = EmployeeService.getInstance();
		try {
			employees = service.getAllEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map renderContext = MacroUtils.defaultVelocityContext();
		renderContext.put("employees", employees);
		return VelocityUtils.getRenderedTemplate("/templates/employeekeeping.vm", renderContext);
	}

	@Override
	public BodyType getBodyType() {
		return BodyType.NONE;
	}

	@Override
	public OutputType getOutputType() {
		return OutputType.BLOCK;
	}

}
