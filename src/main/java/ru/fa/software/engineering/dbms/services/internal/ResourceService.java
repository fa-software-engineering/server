package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Resource;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.ResourceRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.internal.ResourceDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ResourceService extends AbstractSoftDeletableService<Resource, ResourceDto, Resource.EmployeeProjectPK> {

    @Inject
    ResourceRepository resourceRepository;

    @Override
    public Class<ResourceDto> getDtoType() {
        return ResourceDto.class;
    }

    @Override
    public Class<Resource> getEntityType() {
        return Resource.class;
    }

    @Override
    public AbstractSoftDeletableRepository<Resource, Resource.EmployeeProjectPK> getRepository() {
        return resourceRepository;
    }
}
