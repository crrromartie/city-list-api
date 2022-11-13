package pl.solberg.cities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.solberg.cities.dto.CityResponseDto;
import pl.solberg.cities.dto.CitySaveDto;
import pl.solberg.cities.dto.CityUpdateDto;
import pl.solberg.cities.entity.City;

import java.util.List;

@Mapper
public interface CityMapper {
    CityResponseDto toCityResponseDto(City city);

    List<CityResponseDto> toListCityResponseDto(List<City> cities);

    @Mapping(target = "id", ignore = true)
    City toCity(CitySaveDto citySaveDto);

    City toCity(CityUpdateDto cityUpdateDto);
}
