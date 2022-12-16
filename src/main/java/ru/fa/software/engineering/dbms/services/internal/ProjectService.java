package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.ProjectRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.internal.ProjectDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProjectService extends AbstractSoftDeletableService<Project, ProjectDto, Long> {

    @Inject
    ProjectRepository projectRepository;

    @Override
    public Class<ProjectDto> getDtoType() {
        return ProjectDto.class;
    }

    @Override
    public Class<Project> getEntityType() {
        return Project.class;
    }

    @Override
    public AbstractSoftDeletableRepository<Project, Long> getRepository() {
        return projectRepository;
    }
}
