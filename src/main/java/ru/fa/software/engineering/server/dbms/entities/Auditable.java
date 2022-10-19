package ru.fa.software.engineering.server.dbms.entities;

import java.time.OffsetDateTime;

public interface Auditable {
    OffsetDateTime getDeletedAt();

    void setDeletedAt(OffsetDateTime deletedAt);
}