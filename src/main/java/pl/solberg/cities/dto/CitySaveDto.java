package pl.solberg.cities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "City save dto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitySaveDto {

    @Schema(description = "City name", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Photo link", required = true)
    @NotBlank
    private String photo;
}
