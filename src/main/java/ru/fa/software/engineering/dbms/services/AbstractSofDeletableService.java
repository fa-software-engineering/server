package ru.fa.software.engineering.dbms.services;

import ru.fa.software.engineering.dbms.orm.SuperEntity;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dto.AbstractDto;

import java.io.Serializable;

public abstract class AbstractSofDeletableService<
        EntityType extends SuperEntity<IdType>,
        DtoType extends AbstractDto<IdType>,
        IdType extends Serializable> extends AbstractService<EntityType, DtoType, IdType> {

    public abstract AbstractSoftDeletableRepository<EntityType, IdType> getRepository();


    public void deleteSoftById(IdType id) {
        getRepository().deleteSoftById(id);
    }
}
