package pl.solberg.cities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.solberg.cities.validator.NameOrPhotoNotBlank;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "City update dto")
@NameOrPhotoNotBlank
@Data
public class CityUpdateDto {

    @Schema(description = "City id", required = true)
    @NotNull
    private Long id;

    @Schema(description = "City Name")
    private String name;

    @Schema(description = "Photo link")
    private String photo;
}
