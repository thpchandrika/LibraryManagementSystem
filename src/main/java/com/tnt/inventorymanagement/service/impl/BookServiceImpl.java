package com.tnt.inventorymanagement.service.impl;

import com.tnt.inventorymanagement.config.ModelMapperConfig;
import com.tnt.inventorymanagement.dto.BookDTO;
import com.tnt.inventorymanagement.model.Book;
import com.tnt.inventorymanagement.repository.BookRepository;
import com.tnt.inventorymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

//    @Autowired
//    private BookRepository bookRepository;
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<BookDTO> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookList = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
        return bookList;
    }

    @Override
    public BookDTO getBookById(Long id) {
       Book book = bookRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Book not found"));
       return  modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
       Book book = modelMapper.map(bookDTO, Book.class);
       Book savedBook = bookRepository.save(book);
       return  modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());

        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    @Override
    public void deleteBook(Long id) {
      bookRepository.deleteById(id);
    }
}
