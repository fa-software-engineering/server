package ru.fa.software.engineering.dbms.orm.internal;

import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dbms.orm.AbstractSuperEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class InternalEntity<IdType extends Long> extends AbstractSuperEntity<IdType> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private IdType id;

}