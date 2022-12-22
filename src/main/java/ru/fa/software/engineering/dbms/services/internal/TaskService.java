package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Task;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.TaskRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.internal.TaskDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TaskService extends AbstractSoftDeletableService<Task, TaskDto, Long> {

    @Inject
    TaskRepository taskRepository;


    @Override
    public Class<TaskDto> getDtoType() {
        return TaskDto.class;
    }

    @Override
    public Class<Task> getEntityType() {
        return Task.class;
    }

    @Override
    public AbstractSoftDeletableRepository<Task, Long> getRepository() {
        return taskRepository;
    }
}
