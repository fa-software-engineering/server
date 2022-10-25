package ru.fa.engineering.server.dbms.entities.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "resources")
public class ResourceOld extends InternalEntity<Long> {
    private String name;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

}
