package com.example.demo.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  protected ResponseEntity<?> handleMethodInvalid(org.springframework.web.bind.MethodArgumentNotValidException e) {
    e.printStackTrace();
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON)
        .body((new CustomException("요청 데이터 중 필수 데이터가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)).getMessage());
  }

  @ExceptionHandler(org.springframework.validation.BindException.class)
  protected ResponseEntity<?> handleMethodInvalid2(org.springframework.validation.BindException e) {
    e.printStackTrace();
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON)
            .body((new CustomException("요청 데이터 중 필수 데이터가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)).getMessage());
  }

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<?> handleCustomException(CustomException e) {
    return ResponseEntity.status(e.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
        .body(e.getMessage());
  }

  @ExceptionHandler({JsonProcessingException.class, JsonMappingException.class})
  protected ResponseEntity<?> handleJacksonException() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
        .body(new CustomException("서버 내에서 JSON 파싱 또는 객체 매핑에 오류가 있습니다.", HttpStatus.INTERNAL_SERVER_ERROR).getMessage());
  }

  @ExceptionHandler(org.springframework.http.converter.HttpMessageNotWritableException.class)
  protected ResponseEntity<?> handleJsonWriteParseError(org.springframework.http.converter.HttpMessageNotWritableException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
        .body(new CustomException("응답 JSON 데이터 생성에 문제가 있습니다. 서버에 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR).getMessage());
  }

  @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
  protected ResponseEntity<?> handleJsonReadParseError() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
        .body(new CustomException("요청 JSON 데이터 파싱에 문제가 있습니다. API 스펙을 확인해주세요.", HttpStatus.BAD_REQUEST).getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  protected ResponseEntity<?> handleNullPointerException(NullPointerException e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
        .body(new CustomException("객체의 데이터가 null이거나 null 인 값을 참조하고 있습니다.", HttpStatus.INTERNAL_SERVER_ERROR).getMessage());
  }

  // JPA 의존성 추가, 주석 해제 후 사용
  // @ExceptionHandler(javax.persistence.PersistenceException.class)
  // protected ResponseEntity<?> handlePersistenceException(javax.persistence.PersistenceException e) {
  //   e.printStackTrace();
  //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
  //           .contentType(MediaType.APPLICATION_JSON)
  //           .body(new CustomException("관계형 데이터베이스의 연산, 조회 등에 문제가 있습니다. 로그를 참조하세요.", HttpStatus.INTERNAL_SERVER_ERROR)
  //                   .getMessage());
  // }
}
