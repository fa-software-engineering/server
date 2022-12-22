package ru.fa.software.engineering.dto.internal;

import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dto.AbstractDto;

import java.time.OffsetDateTime;

@Getter
@Setter
public class TaskDto extends AbstractDto<Long> {
    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private long projectId;
    private String authorId;
    private String executorId;
}
