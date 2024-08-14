package com.aleksnose.hoteru.Mapper;

import com.aleksnose.hoteru.DTO.TownDTO;
import com.aleksnose.hoteru.models.Town;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TownMapper {
    TownMapper INSTANCE = Mappers.getMapper(TownMapper.class);

    @Mapping(source = "country.id", target = "idCountry")
    TownDTO townToTownDTO(Town town);

    @Mapping(source = "idCountry", target = "country.id")
    Town townDTOToTown(TownDTO townDto);
}
