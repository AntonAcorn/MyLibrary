package ru.acorn.MyLibrary.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.MyLibrary.models.Book;
import ru.acorn.MyLibrary.models.Person;
import ru.acorn.MyLibrary.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//бизнес-логика
@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> toBeFound = peopleRepository.findById(id);
        return toBeFound.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person personToBeUpdated, int id) {
        personToBeUpdated.setId(id);
        peopleRepository.save(personToBeUpdated);
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional <Person> getPersonByFullName(String fullName){
       return peopleRepository.findByFullName(fullName);
    }

    public List<Book> getBooksByPersonId(int id){
        Optional <Person> foundPerson = peopleRepository.findById(id);
        if(foundPerson.isPresent()){
            Hibernate.initialize(foundPerson.get().getBooks());
            foundPerson.get().getBooks().forEach(book ->{
                long expiredTime = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                if(expiredTime > 864000000)
                    book.setExpired(true);
            });
            return foundPerson.get().getBooks();
        }
        else return Collections.emptyList();
    }
}
