package ru.fa.software.engineering.dbms.repositories;

import ru.fa.software.engineering.dbms.orm.SuperEntity;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.OffsetDateTime;


@Transactional
public abstract class AbstractSoftDeletableRepository<
        EntityType extends SuperEntity<IdType>,
        IdType extends Serializable>
        extends AbstractRepository<EntityType, IdType> {

    public boolean deleteSoftById(IdType id) {
        EntityType entity = findById(id);

        if (entity != null) {
            entity.setDeletedAt(OffsetDateTime.now());
            return true;
        }

        return false;
    }

    public EntityType create(EntityType newEntity) {
        newEntity.setCreatedAt(OffsetDateTime.now());
        persist(newEntity);
        return newEntity;
    }
}