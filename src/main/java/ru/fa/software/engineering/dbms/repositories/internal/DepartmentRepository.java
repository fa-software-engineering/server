package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.Department;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentRepository extends AbstractSoftDeletableRepository<Department, Long> {

}
