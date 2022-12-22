package ru.fa.software.engineering.resources.internal;


import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dbms.services.internal.ProjectService;
import ru.fa.software.engineering.dto.internal.ProjectDto;
import ru.fa.software.engineering.resources.AbstractResource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectResource extends AbstractResource<Project, ProjectDto, Long> {
    @Inject
    ProjectService projectService;

    @Override
    public AbstractSoftDeletableService<Project, ProjectDto, Long> getService() {
        return projectService;
    }

    @GET
    @Path("/{project_id}/tasks")
    public Response getTasks(@PathParam("project_id") long projectId) {
        return Response.ok(projectService.getTasksByProjectId(projectId)).build();
    }

    @Override
    @GET
    public Response getAll(Integer skip, Integer limit, String search, String... fields) {
        return super.getAll(skip, limit, search, fields);
    }

    @Override
    @GET
    @Path("/{project_id}")
    public Response getById(@PathParam("project_id") Long id) {
        return super.getById(id);
    }

    @POST
    @Override
    public Response create(ProjectDto dto) {
        return super.create(dto);
    }

    @PUT
    @Path("/{project_id}")
    public Response update(@PathParam("project_id") Long id, ProjectDto dto) {
        dto.setId(id);
        return super.update(dto);
    }

    @DELETE
    @Override
    @Path("/{project_id}")
    public Response delete(@PathParam("project_id") Long id) {
        return super.delete(id);
    }
}
