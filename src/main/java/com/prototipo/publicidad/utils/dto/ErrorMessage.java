package com.prototipo.publicidad.utils.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
