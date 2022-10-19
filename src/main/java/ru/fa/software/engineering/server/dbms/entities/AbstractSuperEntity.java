package ru.fa.software.engineering.server.dbms.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractSuperEntity<IdType extends Serializable> implements SuperEntity<IdType> {
    private OffsetDateTime deletedAt;
}
