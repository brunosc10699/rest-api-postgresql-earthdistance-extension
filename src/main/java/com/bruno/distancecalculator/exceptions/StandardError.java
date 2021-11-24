package com.bruno.distancecalculator.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
