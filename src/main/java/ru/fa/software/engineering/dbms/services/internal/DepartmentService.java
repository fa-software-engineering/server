package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Department;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.DepartmentRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.internal.DepartmentDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DepartmentService extends AbstractSoftDeletableService<Department, DepartmentDto, Long> {

    @Inject
    DepartmentRepository departmentRepository;

    @Override
    public Class<DepartmentDto> getDtoType() {
        return DepartmentDto.class;
    }

    @Override
    public Class<Department> getEntityType() {
        return Department.class;
    }

    @Override
    public AbstractSoftDeletableRepository<Department, Long> getRepository() {
        return departmentRepository;
    }
}
