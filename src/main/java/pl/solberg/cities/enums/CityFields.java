package pl.solberg.cities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CityFields {
    NAME("name"),
    PHOTO("photo");

    private final String code;
}
