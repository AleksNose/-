package com.aleksnose.hoteru.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorDetails {
    private Date date;
    private String message;
    private String details;

}