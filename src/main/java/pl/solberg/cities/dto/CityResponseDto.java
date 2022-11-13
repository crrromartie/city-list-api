package pl.solberg.cities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "City response")
@Data
public class CityResponseDto {

    @Schema(description = "City name", required = true)
    private String name;

    @Schema(description = "Photo link", required = true)
    private String photo;
}
