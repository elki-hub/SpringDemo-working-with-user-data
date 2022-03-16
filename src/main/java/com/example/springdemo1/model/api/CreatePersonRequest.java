package com.example.springdemo1.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor //sukuria konstructoriu
public class CreatePersonRequest {
    @Schema(description = "Person first name", example = "Vardenis")
    @NonNull
    @Size(min = 3, max=20)
    private String firstName;

    @Schema(description = "Person middle name", example = "Vasaris")
    private String middleName;

    @Schema(description = "Person last name", example = "Pavardenis")
    @NonNull
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String lastName;

    @Schema(description = "Person email", example = "email@gmail.com")
    private String email;

    @Schema(description = "Person phone", example = "+37061111111")
    private String phone;
}
