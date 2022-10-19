package ru.fa.software.engineering.server.dbms.entities;

import java.io.Serializable;

public interface SuperEntity<IdType extends Serializable>
        extends Auditable, Identity<IdType> {
}
