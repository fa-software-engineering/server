package ru.fa.software.engineering.dbms.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dbms.orm.Identity;
import ru.fa.software.engineering.utils.CustomQueryUtil;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class AbstractRepository<
        IdType extends Serializable,
        EntityType extends Identity<IdType>>
        implements PanacheRepositoryBase<EntityType, IdType> {


    @Getter
    @Setter
    public static class QueryResult<ResultType> {
        public enum Status {
            OK,
            NOT_FOUND
        }

        QueryResult.Status status;
        ResultType result;
        String errorMessage;

    }

    public abstract String getSimpleClassName();



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
    protected <T> QueryResult<T> getByIdMap(IdType id, Function<EntityType, T> f) {

        QueryResult<T> result = new QueryResult<>();

        var qr1 = getById(id);

        if (qr1.getStatus() == QueryResult.Status.NOT_FOUND) {
            result.setErrorMessage(qr1.getErrorMessage());
            result.setStatus(QueryResult.Status.NOT_FOUND);
            return result;
        }

        var a = f.apply(qr1.getResult());

        result.setResult(a);
        result.setStatus(QueryResult.Status.OK);
        return result;
    }

    @Transactional
    public QueryResult<EntityType> getById(IdType id) {

        QueryResult<EntityType> queryResult = new QueryResult<>();

        EntityType entity = PanacheRepositoryBase.super.findById(id);

        if (entity == null) {
            queryResult.status = QueryResult.Status.NOT_FOUND;
            queryResult.errorMessage = getSimpleClassName() + " with id = " + id + " is not found";
        } else {
            queryResult.status = QueryResult.Status.OK;
        }

        queryResult.result = entity;

        return queryResult;
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
