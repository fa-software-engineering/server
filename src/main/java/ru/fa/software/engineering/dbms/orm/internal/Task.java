package ru.fa.software.engineering.dbms.orm.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.software.engineering.dbms.orm.external.Employee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "tasks")
public class Task extends InternalEntity<Long> {
    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Employee author;

    @ManyToOne
    private Employee executor;
}
