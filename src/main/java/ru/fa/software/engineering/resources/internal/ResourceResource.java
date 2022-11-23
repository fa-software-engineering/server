package ru.fa.software.engineering.resources.internal;

import ru.fa.software.engineering.dbms.orm.internal.Resource;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dbms.services.internal.ResourceService;
import ru.fa.software.engineering.dto.internal.ResourceDto;
import ru.fa.software.engineering.resources.AbstractResource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/resources")
public class ResourceResource extends AbstractResource<Resource, ResourceDto, Resource.EmployeeProjectPK> {

    @Inject
    ResourceService resourceService;

    @Override
    public AbstractSoftDeletableService<Resource, ResourceDto, Resource.EmployeeProjectPK> getService() {
        return resourceService;
    }

    @GET
    public Response getById(@QueryParam("employeeId") String employeeId, @QueryParam("projectId") long projectId) {
        Resource.EmployeeProjectPK id = new Resource.EmployeeProjectPK(employeeId, projectId);
        return super.getById(id);
    }

    @PUT
    public Response update(@QueryParam("employeeId") String employeeId, @QueryParam("projectId") long projectId, ResourceDto dto) {
        dto.setProjectId(projectId);
        dto.setEmployeeId(employeeId);
        return super.update(dto);
    }

    @Override
    @GET
    public Response getAll(Integer skip, Integer limit, String search, String... fields) {
        return super.getAll(skip, limit, search, fields);
    }

    @DELETE
    public Response delete(@QueryParam("employeeId") String employeeId, @QueryParam("projectId") long projectId) {
        Resource.EmployeeProjectPK id = new Resource.EmployeeProjectPK(employeeId, projectId);
        return super.delete(id);
    }
}
