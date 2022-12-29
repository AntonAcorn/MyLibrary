package ru.acorn.MyLibrary.bootStrapData;

import jakarta.persistence.Column;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.acorn.MyLibrary.models.Book;
import ru.acorn.MyLibrary.models.Person;
import ru.acorn.MyLibrary.repositories.BooksRepository;
import ru.acorn.MyLibrary.repositories.PeopleRepository;

//@Component
//public class BootStrapData implements CommandLineRunner {
//    private final BooksRepository booksRepository;
//    private final PeopleRepository peopleRepository;
//
//    public BootStrapData(BooksRepository booksRepository, PeopleRepository peopleRepository) {
//        this.booksRepository = booksRepository;
//        this.peopleRepository = peopleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Person erick = new Person("Erick", 1990);
//        Book newBook = new Book("Digital design", "B. B. Bach", 1990);
//        erick.getBooks().add(newBook);
//        // newBook.getAuthor().add(erick);
//
//        peopleRepository.save(erick);
//        booksRepository.save(newBook);
//
//        System.out.println("Started on bootstrap");
//
//    }
//}
