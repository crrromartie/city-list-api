package pl.solberg.cities.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Exception response dto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponseDto {

    @Schema(description = "Timestamp", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeStamp;

    @Schema(description = "Error message", required = true)
    private String errorMessage;
}
