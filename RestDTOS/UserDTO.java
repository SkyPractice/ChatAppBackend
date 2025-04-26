package com.app.demo.RestDTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public record UserDTO(
        @JsonProperty("username") String userName,
        @JsonProperty("password") String password,
        @JsonProperty("email") String email,
        @JsonProperty("country") String country,
        @JsonProperty("notes") String notes,
        @JsonProperty("file") MultipartFile file) {
}
