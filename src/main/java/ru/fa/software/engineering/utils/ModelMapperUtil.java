package ru.fa.software.engineering.utils;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.SourceGetter;
import ru.fa.software.engineering.dbms.orm.Identity;
import ru.fa.software.engineering.dto.AbstractDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ModelMapperUtil {
    private static final ModelMapper MAPPER = new ModelMapper();


    static {

    }

    public static <S, T> List<T> mapList(Collection<S> source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        return source
                .stream()
                .map(element -> MAPPER.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static void map(Object source, Object destination) {
        MAPPER.map(source, destination);
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return MAPPER.map(source, targetClass);
    }

    private static <ConverterInType, ConverterOutType, DtoType extends AbstractDto<IdType>, EntityType extends Identity<IdType>, IdType extends Serializable>
    void customizeDtoMapping(
            Converter<ConverterInType, ConverterOutType> converter,
            Class<DtoType> dtoClass,
            Class<EntityType> entityClass,
            SourceGetter<DtoType> dtoGetter,
            DestinationSetter<EntityType, ConverterOutType> entitySetter) {

        var typeMap = MAPPER.getTypeMap(dtoClass, entityClass);
        if (typeMap == null) {
            typeMap = MAPPER.createTypeMap(dtoClass, entityClass);
        }

        typeMap.addMappings(mapper -> mapper
                .when(Conditions.isNotNull())
                .using(converter)
                .map(dtoGetter, entitySetter));
    }

    private static <IdType extends Serializable, EntityType extends Identity<IdType>>
    Converter<IdType, EntityType> getFromFKToEntityConvertor(EntityType entity) {
        return c -> {
            entity.setId(c.getSource());
            return entity;
        };
    }

}
