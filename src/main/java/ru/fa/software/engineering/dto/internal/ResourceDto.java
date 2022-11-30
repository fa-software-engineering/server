package ru.fa.software.engineering.dto.internal;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ResourceDto {
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String name;
    private String employeeId;
    private long projectId;
}
