package ru.acorn.MyLibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Getter
    @Setter
    private String fullName;

    @Min(value = 1900, message = "Age should be greater than 1900")
    @Getter
    @Setter
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    List<Book> books;


    public Person() {
    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
}
