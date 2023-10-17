package com.example.platform_mvp.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    List<ExceptionEntity> exceptionEntities;
}
