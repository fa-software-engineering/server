package ru.fa.software.engineering.dto;

import lombok.Data;

import java.util.List;


@Data
public class PageDto<DtoType> {
    private Long total;
    private Integer skip;
    private Integer limit;
    private List<DtoType> data;
}
