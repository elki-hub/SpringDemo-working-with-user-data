package com.example.springdemo1.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UpdatePerson {
    @Schema(description = "Person first name", example = "Vardenis")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 20)
    private String firstName;

    @Schema(description = "Person middle name", example = "Vasaris")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 20)
    private String middleName;

    @Schema(description = "Person last name", example = "Pavardenis")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 20)
    private String lastName;

    @Schema(description = "Person email", example = "email@gmail.com")
    @Size(min = 3, max = 100)
    private String email;

    @Schema(description = "Person phone", example = "+37061111111")
    @Pattern(regexp = "^[+]?[0-9]+$")
    @Size(min = 9, max = 12)
    private String phone;
}
