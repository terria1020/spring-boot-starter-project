package com.example.demo.dto.wrapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseDto<T> {
    private Integer code;
    private T data;

    public static ResponseDto<?> ok() {
        return new ResponseDto<> (
                HttpStatus.OK.value(),
                null
        );
    }

    public ResponseDto(HttpStatus httpStatus, T data) {
        this.code = httpStatus.value();
        this.data = data;
    }

    public ResponseDto(T data) {
        this.code = HttpStatus.OK.value();
        this.data = data;
    }
}
