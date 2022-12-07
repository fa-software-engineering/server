package ru.fa.software.engineering.resources.internal;

import ru.fa.software.engineering.dbms.orm.internal.Department;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dbms.services.internal.DepartmentService;
import ru.fa.software.engineering.dto.internal.DepartmentDto;
import ru.fa.software.engineering.resources.AbstractResource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/departments")
public class DepartmentResource extends AbstractResource<Department, DepartmentDto, Long> {

    @Inject
    DepartmentService departmentService;


    @GET
    public Response getAll(@QueryParam("skip") Integer skip,
                           @QueryParam("limit") Integer limit,
                           @QueryParam("search") String search) {
        return super.getAll(skip, limit, search, "title");
    }

    @Override
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return super.getById(id);
    }

    @Override
    @POST
    public Response create(DepartmentDto dto) {
        return super.create(dto);
    }

    @Override
    @PUT
    public Response update(DepartmentDto dto) {
        return super.update(dto);
    }

    @Override
    @DELETE
    public Response delete(Long id) {
        return super.delete(id);
    }

    @Override
    public AbstractSoftDeletableService<Department, DepartmentDto, Long> getService() {
        return departmentService;
    }
}
