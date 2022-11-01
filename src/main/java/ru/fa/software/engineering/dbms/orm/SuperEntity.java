package ru.fa.software.engineering.dbms.orm;

import java.io.Serializable;

public interface SuperEntity<IdType extends Serializable>
        extends Auditable, Identity<IdType> {
}
