package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.Task;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepository extends AbstractSoftDeletableRepository<Task, Long> {
}
