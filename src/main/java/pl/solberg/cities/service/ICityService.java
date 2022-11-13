package pl.solberg.cities.service;

import pl.solberg.cities.dto.CityResponseDto;
import pl.solberg.cities.dto.CitySaveDto;
import pl.solberg.cities.dto.CityUpdateDto;

import java.util.List;

public interface ICityService {

    void save(CitySaveDto citySaveDto);

    CityResponseDto update(CityUpdateDto cityUpdateDto);

    CityResponseDto findById(Long id);

    List<CityResponseDto> findByName(String name);

    List<CityResponseDto> findPerPage(int page, int size);
}
