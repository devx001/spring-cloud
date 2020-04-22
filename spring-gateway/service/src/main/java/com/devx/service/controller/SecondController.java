package com.devx.service.controller;

import com.devx.service.dto.ExampleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class SecondController {

  @GetMapping("/message")
  public String test() {
    return "Hello JavaInUse Called in Second Service";
  }

  @PostMapping("/dto")
  public ResponseEntity<ExampleDto> examplePost(@RequestBody ExampleDto exampleDto) {
    return ResponseEntity.ok(exampleDto);
  }

}