package ru.project.starter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String nationality;
    private Integer age;
}
