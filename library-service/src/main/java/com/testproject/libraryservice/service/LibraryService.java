package com.testproject.libraryservice.service;

import com.testproject.libraryservice.dto.BookClaimRequest;
import com.testproject.libraryservice.dto.BookClaimResponse;
import com.testproject.libraryservice.exception.BookClaimNotFoundException;
import com.testproject.libraryservice.exception.IncorrectDateException;
import com.testproject.libraryservice.mapper.BookClaimMapper;
import com.testproject.libraryservice.model.BookClaim;
import com.testproject.libraryservice.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookClaimMapper bookClaimMapper;

    public Page<BookClaimResponse> getBookClaimsPage(PageRequest pageRequest) {
        Page<BookClaim> bookClaims = libraryRepository.findAll(pageRequest);
        return bookClaims.map(bookClaim -> bookClaimMapper.toBookClaimResponse(bookClaim));
    }

    public Page<BookClaimResponse> getAvailableBookClaims(PageRequest pageRequest) {
        Page<BookClaim> bookClaims = libraryRepository.findAvailableBookCliams(LocalDateTime.now(), pageRequest);
        return bookClaims.map(bookClaim -> bookClaimMapper.toBookClaimResponse(bookClaim));
    }

    public BookClaimResponse getBookClaimByBookId(Long id) {
        Optional<BookClaim> bookClaim = libraryRepository.getBookClaimByBookId(id);
        if (bookClaim.isPresent()) {
            return bookClaimMapper.toBookClaimResponse(bookClaim.get());
        }
        throw new BookClaimNotFoundException();
    }

    public BookClaimResponse createBookClaim(Long bookId) {
        BookClaim bookClaim = new BookClaim();
        bookClaim.setBookId(bookId);
        bookClaim.setClaimDate(null);
        bookClaim.setReturnDate(null);
        return bookClaimMapper.toBookClaimResponse(libraryRepository.save(bookClaim));
    }

    public BookClaimResponse updateBookClaim(Long bookId, BookClaimRequest bookClaimRequest) {
        BookClaim updatedBookClaim = bookClaimMapper.toBookClaim(bookClaimRequest);
        Optional<BookClaim> bookClaim = libraryRepository.getBookClaimByBookId(bookId);
        if (bookClaim.isPresent()) {
            if (updatedBookClaim.getClaimDate() != null && updatedBookClaim.getReturnDate() != null) {
                bookClaim.get().setClaimDate(updatedBookClaim.getClaimDate());
                bookClaim.get().setReturnDate(updatedBookClaim.getReturnDate());
                return bookClaimMapper.toBookClaimResponse(libraryRepository.save(bookClaim.get()));
            }
            throw new IncorrectDateException("Dates must be non-null");
        }
        //TODO поменять
        throw new BookClaimNotFoundException();
    }

    public void deleteBookClaim(Long id) {
        if (libraryRepository.getBookClaimByBookId(id).isPresent()) {
            libraryRepository.deleteBookClaimByBookId(id);
        } else {
            throw new BookClaimNotFoundException();
        }
    }
}
