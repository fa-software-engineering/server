package ru.fa.software.engineering.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDto<IdType> {
    protected IdType id;
}
