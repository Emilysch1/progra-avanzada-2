package com.programacion.avanzada;

import com.programacion.avanzada.db.Author;
import com.programacion.avanzada.db.Book;
import com.programacion.avanzada.repository.BookRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.math.BigDecimal;
import java.util.List;

public class NosqlMain {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize()) {

            DocumentTemplate template = container.select(DocumentTemplate.class)
                    .get();
            Book book01 = new Book("1111-111", "Effective Java", new BigDecimal("45.00"),
            List.of(
           new Author( 1, "Joshua Bloch"),
            new Author( 2, "James Glosign")
            )
            );

            template.insert(book01);

//            var book = template.find(Book.class, "1234-5678");
//            book.ifPresent(b ->{
//                System.out.println(b);
//            });

            //Repository

            BookRepository repo = container.select(BookRepository.class).get();
                    repo.findAll().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
