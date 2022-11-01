package ru.fa.software.engineering.dbms.orm.external;

import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dbms.orm.AbstractEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class ExternalEntity<IdType extends Serializable> extends AbstractEntity<IdType> {
}