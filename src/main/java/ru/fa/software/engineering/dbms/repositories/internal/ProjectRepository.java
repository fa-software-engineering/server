package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectRepository extends AbstractSoftDeletableRepository<Project, Long> {
}
