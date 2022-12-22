package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.ProjectRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.internal.ProjectDto;
import ru.fa.software.engineering.dto.internal.TaskDto;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProjectService extends AbstractSoftDeletableService<Project, ProjectDto, Long> {

    @Inject
    ProjectRepository projectRepository;

    public List<TaskDto> getTasksByProjectId(long projectId) {
        return ModelMapperUtil.mapList(
                projectRepository.findTasksByProjectId(projectId), TaskDto.class);
    }

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
