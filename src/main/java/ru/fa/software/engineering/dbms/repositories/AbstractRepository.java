package ru.fa.software.engineering.dbms.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import ru.fa.software.engineering.dbms.orm.Identity;
import ru.fa.software.engineering.utils.CustomQueryUtil;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class AbstractRepository<
        EntityType extends Identity<IdType>,
        IdType extends Serializable>
        implements PanacheRepositoryBase<EntityType, IdType> {

    @Transactional
    public List<EntityType> getAll() {
        return listAll();
    }

    public Stream<EntityType> getRangedStream(Stream<EntityType> sourceStream, Integer skip, Integer limit) {

        if (skip != null) {
            sourceStream = sourceStream.skip(skip);
        }

        if (limit != null) {
            sourceStream = sourceStream.limit(limit);
        }

        return sourceStream;
    }

    public List<EntityType> getAll(Integer skip, Integer limit) {
        var query = findAll().stream();
        return getRangedStream(query, skip, limit).collect(Collectors.toList());
    }

    public List<EntityType> getAll(Integer skip, Integer limit, String like, String... fields) {
        var query = find(CustomQueryUtil.getLikeQuery(fields), '%' + like + '%').stream();

        return getRangedStream(query, skip, limit).collect(Collectors.toList());
    }

    @Transactional
    public EntityType getById(IdType id) {
        return findById(id);
    }

    @Transactional
    public EntityType create(@Valid EntityType newEntity) {
        persist(newEntity);
        return newEntity;
    }

    public void deleteHardById(IdType id) {
        flush();
        deleteById(id);
    }


    @Transactional
    public EntityType updateWithRelation(EntityType entity, Consumer<EntityType> deleteRelation) {
        if (isPersistent(entity)) {
            return entity;
        }

        EntityType oldEntity = find("id", entity.getId()).firstResult();

        if (oldEntity != null) {
            if (deleteRelation != null) {
                deleteRelation.accept(oldEntity);
            }

            ModelMapperUtil.map(entity, oldEntity);
            persist(oldEntity);
            return oldEntity;
        }

        throw new NotFoundException("Cannot update. Cause: row is not found.");
    }

    public EntityType update(EntityType entity) {
        return updateWithRelation(entity, null);
    }

    public boolean existsById(IdType id) {
        return findById(id) != null;
    }

}
