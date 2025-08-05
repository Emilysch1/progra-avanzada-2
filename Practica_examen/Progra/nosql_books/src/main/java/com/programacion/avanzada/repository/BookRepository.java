package com.programacion.avanzada.repository;

import com.programacion.avanzada.db.Book;
import jakarta.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
