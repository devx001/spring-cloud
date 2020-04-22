package com.devx.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExampleDto {

  private String name;
  private String lastName;
  private String phoneNumber;

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
        .append("Name: ")
        .append(name)
        .append("Last Name: ")
        .append(lastName)
        .append("Phone Number: ")
        .append(phoneNumber);
    return stringBuilder.toString();
  }
}
