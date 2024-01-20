package com.tnt.inventorymanagement.repository;

import com.tnt.inventorymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
