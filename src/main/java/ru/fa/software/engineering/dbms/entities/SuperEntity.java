package ru.fa.software.engineering.dbms.entities;

import java.io.Serializable;

public interface SuperEntity<IdType extends Serializable>
        extends Auditable, Identity<IdType> {
}
