package pl.solberg.cities.util;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.solberg.cities.dto.CitySaveDto;
import pl.solberg.cities.enums.CityFields;
import pl.solberg.cities.service.CityService;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CsvCitySaver {

    private final CityService cityService;

    @SneakyThrows
    public void saveCitiesFromCsvToDb(String csvPath) {
        var file = new File(csvPath);
        try (var inputStream = new FileInputStream(file)) {
            var parserSettings = new CsvParserSettings();
            parserSettings.setHeaderExtractionEnabled(true);
            var parser = new CsvParser(parserSettings);
            List<Record> records = parser.parseAllRecords(inputStream);
            records.forEach(record -> {
                var city = CitySaveDto.builder()
                        .name(record.getString(CityFields.NAME.getCode()))
                        .photo(record.getString(CityFields.PHOTO.getCode()))
                        .build();
                cityService.save(city);
            });
        }
    }
}
