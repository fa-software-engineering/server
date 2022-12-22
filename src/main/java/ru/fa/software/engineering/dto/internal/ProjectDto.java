package ru.fa.software.engineering.dto.internal;

import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dto.AbstractDto;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ProjectDto extends AbstractDto<Long> {
    private String title;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String ownerId;
}
