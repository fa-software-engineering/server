package ru.fa.engineering.server.dbms.entities.external;

import lombok.Getter;
import lombok.Setter;
import ru.fa.engineering.server.dbms.entities.AbstractEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class ExternalEntity<IdType extends Serializable> extends AbstractEntity<IdType> {
}