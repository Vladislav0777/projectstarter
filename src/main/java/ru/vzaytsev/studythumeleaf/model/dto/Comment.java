package ru.vzaytsev.studythumeleaf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;
    private String text;
}
