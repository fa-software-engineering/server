package ru.fa.software.engineering.dbms.entities;

import java.time.OffsetDateTime;

public interface Auditable {
    OffsetDateTime getCreatedAt();
    OffsetDateTime getDeletedAt();

    void setCreatedAt(OffsetDateTime createdAt);
    void setDeletedAt(OffsetDateTime deletedAt);
}

