package com.example.springdemo1.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //sukuria konstructoriu
public class PersonResponse {
    @Schema(description = "Person first name", example = "Vardenis")
    private String firstName;
    @Schema(description = "Person last name", example = "Pavardenis")
    private String lastName;
    @Schema(description = "Person email", example = "email@gmail.com")
    private String email;
    @Schema(description = "Person phone", example = "+37061111111")
    private String phone;
}
