package ru.fa.software.engineering.dbms.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity<IdType extends Serializable>
        extends PanacheEntityBase implements Identity<IdType> {

    @Id
    private IdType id;
}
