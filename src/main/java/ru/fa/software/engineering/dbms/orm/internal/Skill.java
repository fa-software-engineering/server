package ru.fa.software.engineering.dbms.orm.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "skills")
public class Skill extends InternalEntity<Long> {
    private String title;
    private String description;
}
