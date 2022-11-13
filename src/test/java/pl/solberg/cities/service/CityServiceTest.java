package pl.solberg.cities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.solberg.cities.dto.CityResponseDto;
import pl.solberg.cities.dto.CitySaveDto;
import pl.solberg.cities.dto.CityUpdateDto;
import pl.solberg.cities.entity.City;
import pl.solberg.cities.exception.ResourceNotFoundException;
import pl.solberg.cities.mapper.CityMapper;
import pl.solberg.cities.repository.CityRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @InjectMocks
    private CityService service;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private CityRepository repository;

    @Mock
    private CityMapper mapper;

    @Test
    void saveCityTest() {
        var city = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo link")
                .build();
        var citySaveDto = CitySaveDto.builder()
                .name("London")
                .photo("London photo link")
                .build();

        doNothing().when(repository).save(city);
        when(mapper.toCity(citySaveDto)).thenReturn(city);

        service.save(citySaveDto);

        var cityCapture = ArgumentCaptor.forClass(City.class);

        verify(repository, times(1)).save(cityCapture.capture());

        assertThat(cityCapture.getValue(), is(city));
    }

    @Test
    void updateCityTest() {
        var city = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo link")
                .build();
        var updatedCity = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo-2 link")
                .build();
        var cityUpdateDto = CityUpdateDto.builder()
                .id(1L)
                .name("London")
                .photo("London photo-2 link")
                .build();
        var cityResponseDto = CityResponseDto.builder()
                .name("London")
                .photo("London photo-2 link")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(city));
        when(mapper.toCity(cityUpdateDto)).thenReturn(updatedCity);
        when(repository.update(updatedCity)).thenReturn(updatedCity);
        when(mapper.toCityResponseDto(updatedCity)).thenReturn(cityResponseDto);

        var actual = service.update(cityUpdateDto);

        assertThat(actual, is(cityResponseDto));
    }

    @Test
    void findByIdTest() {
        var city = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo link")
                .build();
        var cityResponseDto = CityResponseDto.builder()
                .name("London")
                .photo("London photo link")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(city));
        when(mapper.toCityResponseDto(city)).thenReturn(cityResponseDto);

        var actual = service.findById(1L);

        assertThat(actual, is(cityResponseDto));
    }

    @Test
    void findByIdShouldThrowExceptionTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void findByNameTest() {
        var city = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo link")
                .build();
        var cityResponseDto = CityResponseDto.builder()
                .name("London")
                .photo("London photo link")
                .build();

        when(repository.findByName("London")).thenReturn(List.of(city));
        when(mapper.toListCityResponseDto(List.of(city))).thenReturn(List.of(cityResponseDto));

        var actual = service.findByName("London");

        assertThat(actual, is(List.of(cityResponseDto)));
    }

    @Test
    void findPerPageTest() {
        var city = City.builder()
                .id(1L)
                .name("London")
                .photo("London photo link")
                .build();
        var cityResponseDto = CityResponseDto.builder()
                .name("London")
                .photo("London photo link")
                .build();

        when(repository.findPerPage(1, 1)).thenReturn(List.of(city));
        when(mapper.toListCityResponseDto(List.of(city))).thenReturn(List.of(cityResponseDto));

        var actual = service.findPerPage(1, 1);

        assertThat(actual, is(List.of(cityResponseDto)));
    }
}
