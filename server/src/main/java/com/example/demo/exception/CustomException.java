package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CustomException extends RuntimeException {
  String message;
  String detail;
  HttpStatus httpStatus;

  public CustomException() {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.message = "잘못된 요청입니다.";
  }

  public CustomException(String message) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.message = message;
  }

  public CustomException(String message, HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
    this.message = message;
  }

  public CustomException(String message, String detail, HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
    this.message = message;
    this.detail = detail;
  }

  @Override
  @SneakyThrows
  public String getMessage() {
    ObjectMapper objectMapper = new ObjectMapper();

    CustomExceptionMessage exceptionMessage = new CustomExceptionMessage(this.httpStatus.value(), this.message);
    return objectMapper.writeValueAsString(exceptionMessage);
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CustomExceptionMessage {
    @JsonProperty(value = "code")
    private int code;
    @JsonProperty(value = "message")
    private String message;
  }


}