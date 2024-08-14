package com.aleksnose.hoteru.Mapper;

import com.aleksnose.hoteru.DTO.ComponentDTO;
import com.aleksnose.hoteru.models.Component;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComponentMapper {
    ComponentMapper INSTANCE = Mappers.getMapper(ComponentMapper.class);

    ComponentDTO componentToComponentDTO(Component entity);

    Component componentDTOToComponent(ComponentDTO componentDto);
}
