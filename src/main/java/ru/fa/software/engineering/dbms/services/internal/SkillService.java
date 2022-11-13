package ru.fa.software.engineering.dbms.services.internal;

import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;
import ru.fa.software.engineering.dbms.repositories.internal.SkillRepository;
import ru.fa.software.engineering.dbms.services.AbstractSoftDeletableService;
import ru.fa.software.engineering.dto.SkillDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SkillService extends AbstractSoftDeletableService<Skill, SkillDto, Long> {

    @Inject
    SkillRepository skillRepository;

    @Override
    public Class<SkillDto> getDtoType() {
        return SkillDto.class;
    }

    @Override
    public Class<Skill> getEntityType() {
        return Skill.class;
    }

    @Override
    public AbstractSoftDeletableRepository<Skill, Long> getRepository() {
        return skillRepository;
    }
}
