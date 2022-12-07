package ru.fa.software.engineering.resources.external;


import ru.fa.software.engineering.dbms.services.external.EmployeeService;
import ru.fa.software.engineering.dto.internal.SkillDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @Path("/{employee_id}/skills")
    @RolesAllowed("employee")
    @GET
    public Response getSkillsByEmployeeId(@PathParam("employee_id") String employeeId) {
        List<SkillDto> skillDto = employeeService.getSkillsByEmployeeId(employeeId);

        if (skillDto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(skillDto).build();
    }

    @Path("/{employee_id}/skills/{skill_id}")
    @RolesAllowed("manager")
    @POST
    public Response addSkillByEmployeeId(@PathParam("employee_id") String employeeId, @PathParam("skill_id") long skillId) {

        boolean isAdded = employeeService.addSkillByEmployeeId(skillId, employeeId);
        if (isAdded) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
