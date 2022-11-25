package ru.acorn.MyLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.MyLibrary.models.Book;
import ru.acorn.MyLibrary.models.Person;
import ru.acorn.MyLibrary.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//бизнес-логика
@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear){
        if(sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if(sortByYear)
            return booksRepository.findAll(PageRequest.of(page,booksPerPage,Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    public List <Book> searchByTitle(String title){
        return booksRepository.findByTitleStartingWith(title);
    }
    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void update(Book updatedBook, int id){//эта книга приходит с формы, поэтому hibernate ee не знает, поэтому метод save
        Book bookToBeUpdated = booksRepository.findById(id).get();
        bookToBeUpdated.setId(id);
        bookToBeUpdated.setOwner(bookToBeUpdated.getOwner());//сущность пришедшая с формы owner - null, поэтому set,чтобы не потерять связь
        booksRepository.save(bookToBeUpdated);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Person getBookOwner(int id){
         //return booksRepository.findById(id).get().getOwner();
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }
    @Transactional
    public void release(int id){ //эта книга persistence контексте, поэтому метод save не нужен, hibernate обновляет таблицу
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }
    @Transactional
    public void assign(int id, Person selectedPerson){
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
            book.setTakenAt(new Date());
        });
    }


}
