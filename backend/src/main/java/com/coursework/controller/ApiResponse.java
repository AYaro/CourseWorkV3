package com.coursework.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"responseEntity"})
public class ApiResponse implements Serializable {
    private int status = 200;
    private String message = "ok";
    private Date timestamp = new Date();

    public String getError() {
        return HttpStatus.valueOf(status).getReasonPhrase();
    }

    public ApiResponse(RuntimeException ex, int status) {
        this.message = ex.getLocalizedMessage();
        this.status = status;
    }

    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public ResponseEntity<ApiResponse> getResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }

    public String dumpToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
