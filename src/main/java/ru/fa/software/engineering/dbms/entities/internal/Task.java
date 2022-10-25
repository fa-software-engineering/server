package ru.fa.software.engineering.dbms.entities.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.software.engineering.dbms.entities.external.KeycloakUser;

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
    private KeycloakUser author;

    @ManyToOne
    private KeycloakUser executor;
}
