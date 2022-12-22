package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.orm.internal.Task;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectRepository extends AbstractSoftDeletableRepository<Project, Long> {
    public List<Task> findTasksByProjectId(long projectId) {
        Project project = getById(projectId);

        if (project != null) {
            return new ArrayList<>(project.getTasks());
        }
        return null;
    }
}
