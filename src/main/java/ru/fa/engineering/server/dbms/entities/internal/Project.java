package ru.fa.engineering.server.dbms.entities.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.engineering.server.dbms.entities.external.KeycloakUser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "projects")
public class Project extends InternalEntity<Long> {
    private String title;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    @ManyToOne
    private KeycloakUser owner;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
