package ru.fa.software.engineering.dto.internal;

import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dto.AbstractDto;

@Getter
@Setter
public class DepartmentDto extends AbstractDto<Long> {
    private String title;
}
