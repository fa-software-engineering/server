package ru.fa.software.engineering.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto extends AbstractDto<Long> {
    private String title;
    private String description;
}
