package ru.fa.software.engineering.resources.internal;

import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dbms.services.internal.SkillService;
import ru.fa.software.engineering.dto.internal.SkillDto;
import ru.fa.software.engineering.resources.AbstractResource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/skills")
public class SkillResource extends AbstractResource<Skill, SkillDto, Long> {

    @Inject
    SkillService skillService;

    @Override
    public AbstractSoftDeletableService<Skill, SkillDto, Long> getService() {
        return skillService;
    }

    @Override
    @GET
    @RolesAllowed("employee")
    public Response getAll() {
        return super.getAll();
    }

    @Override
    @GET
    @RolesAllowed("employee")
    @Path("/{skill_id}")
    public Response getById(@PathParam("skill_id") Long id) {
        return super.getById(id);
    }

    @Override
    @POST
    @RolesAllowed("manager")
    public Response create(SkillDto dto) {
        return super.create(dto);
    }

    @Override
    @PUT
    @RolesAllowed("manager")
    @Path("/{skill_id}")
    public Response update(@PathParam("skill_id")Long id, SkillDto dto) {
        return super.update(id, dto);
    }

    @Override
    @DELETE
    @RolesAllowed("manager")
    @Path("/{skill_id}")
    public Response delete(@PathParam("skill_id") Long id) {
        return super.delete(id);
    }
}
