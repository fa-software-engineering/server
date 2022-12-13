package ru.fa.software.engineering.resources;

import ru.fa.software.engineering.dbms.orm.SuperEntity;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.PageDto;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractResource<
        EntityType extends SuperEntity<IdType>,
        DtoType,
        IdType extends Serializable> {

    public abstract AbstractSoftDeletableService<EntityType, DtoType, IdType> getService();

    public Response getAll() {
        return Response.ok(getService().getAll()).build();
    }

    public Response getAll(Integer skip,
                           Integer limit,
                           String search,
                           String... fields) {
        List<DtoType> dto;

        if (search != null) {
            dto = getService().getAll(skip, limit, search, fields);
        } else {
            dto = getService().getAll(skip, limit);
        }

        PageDto<DtoType> pageDto = new PageDto<>();

        pageDto.setLimit(limit);
        pageDto.setSkip(skip);
        pageDto.setData(dto);
        pageDto.setTotal(getService().getRepository().count());

        return Response.ok(pageDto).build();
    }

    public Response getById(IdType id) {
        DtoType dto = getService().getById(id);

        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    public Response create(DtoType dto) {
        if (dto == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        DtoType created = getService().create(dto);
        return Response.ok(created).build();
    }

    public Response update(DtoType dto) {
        DtoType updated = getService().update(dto);

        if (updated != null) {
            return Response.ok(updated).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response delete(IdType id) {
        boolean deleted = getService().deleteSoftById(id);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}