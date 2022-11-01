package ru.fa.software.engineering.dbms.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractSuperEntity<IdType extends Serializable>
        extends PanacheEntityBase
        implements SuperEntity<IdType> {

    private OffsetDateTime createdAt;
    private OffsetDateTime deletedAt;
}
