package ru.fa.software.engineering.dbms.services;

import ru.fa.software.engineering.dbms.orm.Identity;
import ru.fa.software.engineering.dbms.repositories.AbstractRepository;
import ru.fa.software.engineering.dto.AbstractDto;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractService<
        EntityType extends Identity<IdType>,
        DtoType extends AbstractDto<IdType>,
        IdType extends Serializable> {

    public abstract AbstractRepository<EntityType, IdType> getRepository();

    public abstract Class<DtoType> getDtoType();


    public DtoType getById(IdType id) {
        EntityType entity = getRepository().getById(id);

        if (entity == null) {
            return null;
        }

        return ModelMapperUtil.map(entity, getDtoType());
    }

    public List<DtoType> getAll(IdType id) {
        return ModelMapperUtil.mapList(getRepository().getAll(), getDtoType());
    }

    public List<DtoType> getAll(Integer skip, Integer limit) {
        return ModelMapperUtil.mapList(getRepository().getAll(skip, limit), getDtoType());
    }

    public List<DtoType> getAll(Integer skip, Integer limit, String like, String... fields) {
        return ModelMapperUtil.mapList(getRepository().getAll(skip, limit, like, fields), getDtoType());
    }

    public DtoType create(EntityType entity) {
        return ModelMapperUtil.map(getRepository().create(entity), getDtoType());
    }

    public DtoType update(EntityType entity) {
        return ModelMapperUtil.map(getRepository().update(entity), getDtoType());
    }

    public void deleteHardById(IdType id) {
        getRepository().deleteHardById(id);
    }

    public boolean existsById(IdType id) {
        return getRepository().existsById(id);
    }
}
