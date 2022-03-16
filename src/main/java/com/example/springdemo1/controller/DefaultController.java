package com.example.springdemo1.controller;

import com.example.springdemo1.model.api.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/hello")
@Tag(name = "Default Controller", description = "My first Request")
public class DefaultController {

    @GetMapping(value = "/world")
    @Operation(summary = "Get hello world")
    @ApiResponse(
            responseCode = "200",
            description ="A string object",
            content = @Content(schema = @Schema(implementation = String.class)))
    public String helloWorld(){
        return "Hello World from Spring!";
    }
}
