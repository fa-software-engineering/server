package ru.fa.engineering.server.dbms.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity<IdType extends Serializable> implements Identity<IdType> {

    @Id
    private IdType id;
}
