package com.testproject.libraryservice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BookClaimResponse {
    private Long bookId;
    private Timestamp claimDate;
    private Timestamp returnDate;
}
