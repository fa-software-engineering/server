package ru.fa.software.engineering.dbms.repositories.internal;

import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.repositories.AbstractSoftDeletableRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillRepository extends AbstractSoftDeletableRepository<Skill, Long> {
}
