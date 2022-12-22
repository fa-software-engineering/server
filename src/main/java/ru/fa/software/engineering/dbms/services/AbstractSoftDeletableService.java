package ru.fa.software.engineering.dbms.services;

import ru.fa.software.engineering.dbms.orm.SuperEntity;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import java.io.Serializable;

public abstract class AbstractSoftDeletableService<
        EntityType extends SuperEntity<IdType>,
        DtoType,
        IdType extends Serializable> extends AbstractService<EntityType, DtoType, IdType> {

    public abstract AbstractSoftDeletableRepository<EntityType, IdType> getRepository();


    public boolean deleteSoftById(IdType id) {
        return getRepository().deleteSoftById(id);
    }
}
