package ru.fa.software.engineering.dbms.repositories;

import ru.fa.software.engineering.dbms.orm.internal.Skill;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillRepository extends AbstractSoftDeletableRepository<Skill, Long> {
}
