package ru.fa.software.engineering.rasserver.dbms.entities;

import java.io.Serializable;

public interface Identity<IdType extends Serializable> {
    IdType getId();
    void setId();
}
