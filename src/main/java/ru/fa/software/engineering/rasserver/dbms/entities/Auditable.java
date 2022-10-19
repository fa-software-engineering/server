package ru.fa.software.engineering.rasserver.dbms.entities;

import java.time.OffsetDateTime;

public interface Auditable {
    OffsetDateTime getDeletedAt();

    void setDeletedAt(OffsetDateTime deletedAt);
}