package com.testproject.bookservice.dto;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}
