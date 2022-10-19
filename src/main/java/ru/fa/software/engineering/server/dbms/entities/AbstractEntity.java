package ru.fa.software.engineering.server.dbms.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity<IdType extends Serializable> implements Identity<IdType> {

    @Id
    private IdType id;
}
