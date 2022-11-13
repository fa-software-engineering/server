package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeSkillRepository extends
        AbstractSoftDeletableRepository<EmployeeSkill, EmployeeSkill.EmployeeSkillPK> {

}
