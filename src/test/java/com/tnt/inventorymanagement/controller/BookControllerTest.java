package com.tnt.inventorymanagement.controller;

import com.tnt.inventorymanagement.dto.BookDTO;
import com.tnt.inventorymanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private  BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    public void getAllBooksTest(){
        List<BookDTO> mockbooks = Arrays.asList(
                new BookDTO( "Book1", "Author1"),
                new BookDTO("Book2", "Author2")
        );

        when(bookService.getAllBooks()).thenReturn(mockbooks);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockbooks, response.getBody());
    }

    @Test
    public  void getBookByIdTest(){
        Long bookId = 1L;

        BookDTO book = new BookDTO("Title1", "Author1");

        when(bookService.getBookById(bookId)).thenReturn(book);

        ResponseEntity<BookDTO> response = bookController.getBookById(bookId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public  void createBookTest(){

        BookDTO reqBook = new BookDTO("Title1", "Author1");
        BookDTO mockBook = new BookDTO("Title1", "Author1");

        when(bookService.createBook(reqBook)).thenReturn(mockBook);

        ResponseEntity<BookDTO> response = bookController.createBook(reqBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reqBook, response.getBody());
    }

    @Test
    public  void updateBookTest(){

        Long bookId = 1L;

        BookDTO reqBook = new BookDTO("Title1_Ram", "Author1_Ram");
        BookDTO mockUpdatedBook = new BookDTO("Title1_Ram", "Author1_Ram");

        when(bookService.updateBook(bookId,reqBook)).thenReturn(mockUpdatedBook);

        ResponseEntity<BookDTO> response = bookController.updateBook(bookId,reqBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reqBook, response.getBody());
    }

    @Test
    public  void deleteBookTest(){
        Long bookId = 1L;

        doNothing().when(bookService).deleteBook(bookId);

        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        verify(bookService, times(1)).deleteBook(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
