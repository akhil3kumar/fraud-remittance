package exception;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ErrorResponse(
        String errorCode,
        String message,
        LocalDateTime timestamp,
        String path
) {
}
