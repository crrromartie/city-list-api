package pl.solberg.cities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solberg.cities.dto.CityResponseDto;
import pl.solberg.cities.dto.CitySaveDto;
import pl.solberg.cities.dto.CityUpdateDto;
import pl.solberg.cities.entity.City;
import pl.solberg.cities.exception.ResourceNotFoundException;
import pl.solberg.cities.mapper.CityMapper;
import pl.solberg.cities.repository.ICityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class CityService implements ICityService {

    private final ICityRepository cityRepository;
    private final CityMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public void save(CitySaveDto citySaveDto) {
        cityRepository.save(mapper.toCity(citySaveDto));
    }

    @Override
    public CityResponseDto update(CityUpdateDto cityUpdateDto) {
        var existingCity = findByIdOrThrow(cityUpdateDto.getId());
        var updatedCity = mapper.toCity(cityUpdateDto);
        var city = cityRepository.update(mergeCities(existingCity, updatedCity));
        return mapper.toCityResponseDto(city);
    }

    @Override
    public CityResponseDto findById(Long id) {
        var city = findByIdOrThrow(id);
        return mapper.toCityResponseDto(city);
    }

    @Override
    public List<CityResponseDto> findByName(String name) {
        List<City> cities = cityRepository.findByName(name);
        return mapper.toListCityResponseDto(cities);
    }

    @Override
    public List<CityResponseDto> findPerPage(int page, int size) {
        List<City> cities = cityRepository.findPerPage(page, size);
        return mapper.toListCityResponseDto(cities);
    }

    private City findByIdOrThrow(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("City with id = %s not found", id)));
    }

    private City mergeCities(City existingCity, City updatedCity) {
        Map<String, Object> existingData = objectMapper.convertValue(existingCity, Map.class);
        Map<String, Object> updatedData = objectMapper.convertValue(updatedCity, Map.class);
        existingData.putAll(updatedData);
        return objectMapper.convertValue(existingData, City.class);
    }
}
