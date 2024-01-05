package com.example.demo.controller;

import com.example.demo.dto.response.ResExampleDto;
import com.example.demo.dto.wrapper.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<ResExampleDto>> home() {
        return ResponseEntity.ok(new ResponseDto<>(new ResExampleDto("demo1")));
    }

}
