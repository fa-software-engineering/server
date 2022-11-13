package ru.fa.software.engineering.dbms.repositories;

import ru.fa.software.engineering.dbms.orm.SuperEntity;

import java.io.Serializable;
import java.time.OffsetDateTime;


public abstract class AbstractSoftDeletableRepository<
        IdType extends Serializable,
        EntityType extends SuperEntity<IdType>>
        extends AbstractRepository<IdType, EntityType> {

    public void deleteSoftById(IdType id) {
        EntityType entity = findById(id);

        if (entity != null) {
            entity.setDeletedAt(OffsetDateTime.now());
            persistAndFlush(entity);
        }
    }
}