package ru.fa.software.engineering.dbms.orm;

import java.io.Serializable;

public interface Identity<IdType extends Serializable> {
    IdType getId();
    void setId(IdType id);
}
